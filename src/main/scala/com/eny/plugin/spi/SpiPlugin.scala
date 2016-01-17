package com.eny.plugin.spi

import java.io.File

import sbt._

object Imports {

  val mapExport = taskKey[Set[String]]("Generates map of interfaces to implementations")

  object SpiKeys {
    val spiPaths = settingKey[Seq[String]]("Interfaces search folder")
    val traits = settingKey[Seq[String]]("Traits to export")
    val implPaths = settingKey[Seq[String]]("Implementations search folder")
    val exportPath = settingKey[String]("Path to export file")
  }
}

object SpiPlugin extends AutoPlugin {

  import com.eny.plugin.spi.Imports._

  override def projectSettings: Seq[Def.Setting[_]] = {
    Seq(
      SpiKeys.spiPaths := Seq("."),
      SpiKeys.implPaths := Seq("."),
      SpiKeys.traits := Seq(),
      SpiKeys.exportPath := "export.txt",
      mapExport := {
        println(SpiKeys.spiPaths.value)
        println(SpiKeys.implPaths.value)
        println(SpiKeys.traits.value)
        println(SpiKeys.exportPath.value)
        new MapImplementations(SpiKeys.spiPaths.value, SpiKeys.traits.value, SpiKeys.implPaths.value).run()
          .withFilter { case (_, impls) => impls.size > 0 }
          .map {
            case (spi, impls) =>
              new Export(new File(SpiKeys.exportPath.value, spi).getAbsolutePath, impls).run()
              spi
          }
          .toSet
      }
    )
  }
}
