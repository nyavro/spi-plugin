import sbt.Keys._
import sbt._

object ProjectBuild extends Build {

  lazy val commonSettings =  Seq(
    version in ThisBuild := "1.0.3",
    organization in ThisBuild := "com.github.nyavro"
  )

  lazy val itSettings =
    inConfig(IntegrationTest)(Defaults.testSettings) ++
      Seq(
        fork in IntegrationTest := false,
        parallelExecution in IntegrationTest := false
      )

  val root =
    (project in file("."))
    .configs(config("it") extend Runtime)
    .settings(commonSettings ++ itSettings:_*)
    .settings(
      scalaVersion := "2.10.4",
      resolvers += Resolver.bintrayRepo("nyavro", "sbt-plugins"),
      name := "sbt-spi-plugin",
      description := "Automates exporting implementation lists for later use with ServiceLoader",
      sbtPlugin := true,
      publishArtifact in Test := false,
      libraryDependencies ++= Seq(
        "org.clapper" % "classutil_2.10" % "1.0.6",
        "org.scalatest" % "scalatest_2.10" % "3.0.0-M14" % "it, test",
        "org.scalamock" % "scalamock-scalatest-support_2.10" % "3.2.2" % "test"
      ),
      // Bintray publishing
      publishMavenStyle := false,
      licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
    )
}
