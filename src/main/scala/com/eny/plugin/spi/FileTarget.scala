package com.eny.plugin.spi

import java.io.PrintWriter

/**
 * Represents export to file
 * @param path - path to export file
 */
class FileTarget(path:String) {

  /**
   * Opens file, writes strings to it and closes the file
   * @param strings - strings to write to file at path
   */
  def write(strings: Iterable[String]) = printToFile(path) { writer => strings.foreach(writer.println) }

  private def printToFile(file:String)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    }
    finally {
      writer.close()
    }
  }
}
