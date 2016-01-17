package com.eny.plugin.spi

import org.scalatest.Matchers
import org.scalatest.WordSpec

class InterfacesListTest extends WordSpec with Matchers {
  "InterfacesList" should {
    "detect all interfaces of root folder" in {
      new InterfacesList(".").interfaces should === (Set("test.spi.ServiceASpi","test.spi.ServiceBSpi"))
    }
  }
}
