sbtPlugin := true

name := "sbt-spi-plugin"

organization := "com.github.nyavro"

version := "1.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.clapper" % "classutil_2.10" % "1.0.6",
  "org.scalatest" % "scalatest_2.10" % "3.0.0-M14" % "test",
  "org.scalamock" % "scalamock-scalatest-support_2.10" % "3.2.2" % "test"
)

//Publishing:

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>http://github.com/nyavro</url>
    <licenses>
      <license>
        <name>BSD-style</name>
        <url>http://www.opensource.org/licenses/bsd-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:nyavro/spi-plugin.git</url>
      <connection>scm:git:git@github.com:nyavro/spi-plugin.git</connection>
    </scm>
    <developers>
      <developer>
        <id>nyavro</id>
        <name>Evgeniy Nyavro</name>
        <url>http://github.com/nyavro</url>
      </developer>
    </developers>
)
