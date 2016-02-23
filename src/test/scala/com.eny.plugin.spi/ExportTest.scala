package com.eny.plugin.spi

import java.io.File

import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class ExportTest extends WordSpec with Matchers {
  "Export" should {
    "write lines to a file" in {
      new FileTarget("res.txt").write(List("valueA", "valueB"))
      Source
        .fromFile("res.txt")
        .getLines()
        .toList should === (List("valueA", "valueB"))
      new File("res.txt").delete() should === (true)
    }
  }
}
