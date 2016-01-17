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

  lazy val target = impl.base.getAbsolutePath + "/target"
  lazy val mapExport = TaskKey[Set[String]]("mapExport")

  lazy val impl:Project = Project(
    id = "impl",
    base = file("impl"),
    settings = super.settings ++ sharedSettings ++ Seq(
      exportJars := true
    )
  )
    .settings(
      SpiKeys.spiPaths := Seq(spi.base.getAbsolutePath),
      SpiKeys.implPaths := Seq(impl.base.getAbsolutePath),
      SpiKeys.exportPath := target,
      mappings in (Compile, packageBin) ++=
        mapExport.dependsOn(compile in Compile).value.map (
          spi => new java.io.File(target, spi) -> ("META-INF/services/" + spi)
        ).toSeq
    )
    .dependsOn(spi)
    .enablePlugins(SpiPlugin)

  lazy val spiClient = Project(
    id = "spiClient",
    base = file("spiClient"),
    settings = super.settings ++ sharedSettings
  )
    .dependsOn(spi, impl % "runtime")

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