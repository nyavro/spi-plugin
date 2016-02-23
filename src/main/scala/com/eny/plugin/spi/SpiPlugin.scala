package com.eny.plugin.spi

import java.io.File

import sbt._
import sbt.Keys._

object Imports {

  val mapExport = taskKey[Set[String]]("Generates map of interfaces to implementations")

  object SpiKeys {
    val spiPaths = settingKey[Seq[String]]("Interfaces search folder")
    val traits = settingKey[Seq[String]]("Traits to export")
    val implPaths = settingKey[Seq[String]]("Implementations search folder")
  }
}

object SpiPlugin extends AutoPlugin {

  import com.eny.plugin.spi.Imports._

  override def projectSettings: Seq[Def.Setting[_]] = {
    Seq(
      SpiKeys.spiPaths := Seq("."),
      SpiKeys.implPaths := Seq("."),
      SpiKeys.traits := Seq(),
      mapExport <<= (baseDirectory, target, SpiKeys.spiPaths, SpiKeys.implPaths, SpiKeys.traits, streams).map {
        (base, targ, spi, impl, traits, str) =>
          str.log.info("spi-plugin: Generating entries for export")
          str.log.info(s"""spi-plugin: Interfaces source directories: [${spi.mkString(",")}]""")
          str.log.info(s"""spi-plugin: Traits(Interfaces) to export: [${traits.mkString(",")}]""")
          str.log.info(s"""spi-plugin: Implementations source directories: [${impl.mkString(",")}]""")
          new MapImplementations(spi, traits, impl).run()
            .withFilter { case (_, impls) => impls.size > 0 }
            .map {
              case (sp, imp) =>
                str.log.info(s"""spi-plugin: Exporting implementations of $sp: [${imp.mkString(",")}]""")
                new FileTarget(new File(targ, sp).getAbsolutePath).write(imp)
                sp
            }
            .toSet
      }
    )
  }
}
