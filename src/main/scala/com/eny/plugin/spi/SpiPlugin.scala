package com.eny.plugin.spi

import java.io.File

import sbt.Keys._
import sbt._

object Imports {

  val mapExport = taskKey[Set[String]]("Generates map of interfaces to implementations")

  object SpiKeys {
    val spiPaths = settingKey[Seq[String]]("Interfaces search folder")
    val traits = settingKey[Seq[String]]("Traits to export")
    val implPaths = settingKey[Seq[String]]("Implementations search folder")
    val spiModules = settingKey[Seq[ModuleID]]("Modules to search for interfaces")
  }
}

object SpiPlugin extends AutoPlugin {

  import com.eny.plugin.spi.Imports._

  override def projectSettings: Seq[Def.Setting[_]] = {
    Seq(
      SpiKeys.spiPaths := Seq("."),
      SpiKeys.implPaths := Seq("."),
      SpiKeys.traits := Seq(),
      SpiKeys.spiModules := Seq(),
      exportJars := true,
      mapExport <<= (target, SpiKeys.spiPaths, SpiKeys.implPaths, SpiKeys.traits, SpiKeys.spiModules, streams).map {
        (targ, spiSources, impl, traits, modules, str) =>
          str.log.info("---- spi-plugin: Generating entries for export")
          str.log.info(s"""---- spi-plugin: Interfaces sources: [${spiSources.mkString(",")}]""")
          str.log.info(s"""---- spi-plugin: Traits(Interfaces) to export: [${traits.mkString(",")}]""")
          str.log.info(s"""---- spi-plugin: Implementations source directories: [${impl.mkString(",")}]""")
          new MapImplementations(spiSources, traits, impl).run()
            .withFilter { case (_, impls) => impls.nonEmpty }
            .map {
              case (sp, imp) =>
                str.log.info(s"""---- spi-plugin: Exporting implementations of $sp: [${imp.mkString(",")}]""")
                new FileTarget(new File(targ, sp).getAbsolutePath).write(imp)
                sp
            }
            .toSet
      },
      mappings in (Compile, packageBin) <<= (mappings in (Compile, packageBin), mapExport, baseDirectory, target, streams) map {
        (Mappings, Exports, Base, Target, Streams) =>
          Streams.log.info("spi-plugin: -------------------------- Exporting to META-INF/services/ --------------------------")
          Mappings ++ Exports.map(item => new java.io.File(Target, item) -> ("META-INF/services/" + item)).toSeq
      }
    )
  }
}
