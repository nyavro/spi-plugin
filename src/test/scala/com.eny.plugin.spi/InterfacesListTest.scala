package com.eny.plugin.spi

import org.scalatest.Matchers
import org.scalatest.WordSpec

class InterfacesListTest extends WordSpec with Matchers {
  "InterfacesList" should {
    "detect all interfaces of root folder" in {
      new InterfacesList("./target").interfaces should === (Set("test.spi.ServiceASpi","test.spi.ServiceBSpi"))
    }
    "detect all interfaces inside jar" in {
      new InterfacesList(getClass.getResource("spi.jar").getPath).interfaces should === (Set("com.eny.spi_plugin.sample.spi.Greeting"))
    }
  }
}
