import com.eny.plugin.spi.Imports.SpiKeys
import com.eny.plugin.spi.SpiPlugin
import sbt.Keys._
import sbt._

object ProjectBuild extends Build {

  val ScalaVersion = "2.11.7"

  lazy val parent = Project(
    id = "parent",
    base = file("."),
    settings = super.settings ++ sharedSettings
  )
  .settings(
    name := "spi-plugin Sample"
  )
  .aggregate(spi, impl, spiClient)

  lazy val spi = Project(
    id = "spi",
    base = file("spi"),
    settings = super.settings ++ sharedSettings
  )

  lazy val mapExport = TaskKey[Set[String]]("mapExport")

  lazy val impl:Project = Project(
    id = "impl",
    base = file("impl"),
    settings = super.settings ++ sharedSettings ++ SpiPlugin.projectSettings
  )
    .settings(                                                                    // Step 1: Configure spi-plugin
      SpiKeys.spiPaths := Seq(spi.base.getAbsolutePath),                          //   spiPaths - interfaces sources
      SpiKeys.implPaths := Seq(impl.base.getAbsolutePath)                         //   impPaths - implementation sources
  )
    .dependsOn(spi)
    .enablePlugins(SpiPlugin)                                                     // Step 2: Enable spi-plugin

  lazy val spiClient = Project(
    id = "spiClient",
    base = file("spiClient"),
    settings = super.settings ++ sharedSettings
  )
    .dependsOn(spi, impl % "runtime")                                             // Step 3: Mark 'impl' dependency runtime

  lazy val sharedSettings = super.settings ++ Seq(
    version := "1.0.0",
    scalaVersion := ScalaVersion,
    scalaBinaryVersion:= CrossVersion.binaryScalaVersion(ScalaVersion),
    autoCompilerPlugins := true,
    scalacOptions ++= Seq(
      "-language:postfixOps",
      "-language:implicitConversions",
      "-language:reflectiveCalls",
      "-language:higherKinds",
      "-language:existentials",
      "-Yinline-warnings",
      "-Xlint",
      "-deprecation",
      "-feature",
      "-unchecked"
    ),
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
  )
}