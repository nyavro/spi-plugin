package com.eny.plugin.spi

import org.scalatest.{Matchers, WordSpec}

class ImplementationsListTest extends WordSpec with Matchers {
  "ImplementationsList" should {
    "detect all implementations of given interfaces" in {
      new ImplementationsList(Set("test.spi.ServiceASpi"), Seq("./target")).implementations should === (Set("test.impl.ServiceAImpl","test.impl.ServiceABImpl"))
      new ImplementationsList(Set("test.spi.ServiceBSpi"), Seq("./target")).implementations should === (Set("test.impl.ServiceBImpl","test.impl.ServiceABImpl"))
    }
    "detect all implementations of given interfaces inside jar" in {
      new ImplementationsList(
        Set("com.eny.spi_plugin.sample.spi.Greeting"),
        Seq(getClass.getResource("impl.jar").getFile)
      ).implementations should === (Set("com.eny.spi_plugin.sample.spi.GreetingImpl"))
    }
  }
}
