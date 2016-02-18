import java.io.File

import org.scalatest.{Matchers, WordSpec}

import sys.process._

class SpiPluginIntegrationTest extends WordSpec with Matchers {

  "Sample project" should {
    "built successful" in {
      Process(
        s"""sbt clean package""",
        new File("spi-plugin-sample")
      ).!! should include("success")
    }
    "run GreetingImpl in runtime" in {
      Process(
        "sh ./run.sh",
        new File("spi-plugin-sample")
      ).!! should include ("Greeting from GreetingImpl!!!")
    }
  }
}
