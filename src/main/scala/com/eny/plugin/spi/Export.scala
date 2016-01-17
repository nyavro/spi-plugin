package com.eny.plugin.spi

import java.io.PrintWriter

class Export(path:String, values: Iterable[String]) {

  def run() = printToFile(path) { writer => values.foreach(writer.println) }

  def printToFile(file:String)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    }
    finally {
      writer.close()
    }
  }
}
