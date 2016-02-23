package com.eny.plugin.spi

import org.scalatest.{Matchers, WordSpec}

class MapImplementationsTest extends WordSpec with Matchers {
  "ImplementationsList" should {
    "detect all implementations of given interfaces location" in {
      new MapImplementations(Seq("./target"), Seq(), Seq(".")).run should === (
        Map(
          "test.spi.ServiceASpi" -> Set("test.impl.ServiceAImpl", "test.impl.ServiceABImpl"),
          "test.spi.ServiceBSpi" -> Set("test.impl.ServiceBImpl", "test.impl.ServiceABImpl")
        )
      )
    }
    "detect all implementations of given interface" in {
      new MapImplementations(Seq(), Seq("test.spi.ServiceASpi"), Seq(".")).run should === (
        Map(
          "test.spi.ServiceASpi" -> Set("test.impl.ServiceAImpl", "test.impl.ServiceABImpl")
        )
      )
    }
    "detect all implementations inside jar of given interfaces located in jar" in {
      new MapImplementations(Seq(getClass.getResource("spi.jar").getFile), Seq(), Seq(getClass.getResource("impl.jar").getFile)).run should === (
        Map(
          "com.eny.spi_plugin.sample.spi.Greeting" -> Set("com.eny.spi_plugin.sample.spi.GreetingImpl")
        )
      )
    }
  }
}
