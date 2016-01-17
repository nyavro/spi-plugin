package com.eny.plugin.spi

import org.scalatest.{Matchers, WordSpec}

class ImplementationsListTest extends WordSpec with Matchers {
  "ImplementationsList" should {
    "detect all implementations of given interfaces" in {
      new ImplementationsList(Set("test.spi.ServiceASpi"), Seq(".")).implementations should === (Set("test.impl.ServiceAImpl","test.impl.ServiceABImpl"))
      new ImplementationsList(Set("test.spi.ServiceBSpi"), Seq(".")).implementations should === (Set("test.impl.ServiceBImpl","test.impl.ServiceABImpl"))
    }
  }
}
