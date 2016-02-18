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
  }
}
