package com.eny.spi_plugin.sample.client

import java.util.ServiceLoader

import com.eny.spi_plugin.sample.spi.Greeting
import scala.collection.JavaConversions._

object Client {

  def main (args: Array[String]) =
    println(
      ServiceLoader
        .load(classOf[Greeting])
        .headOption
        .map {
          greeting => greeting.greet()
        }
        .getOrElse("Greeting implementation not found")
    )
}
