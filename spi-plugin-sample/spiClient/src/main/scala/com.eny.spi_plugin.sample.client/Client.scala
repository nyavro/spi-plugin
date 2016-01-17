package com.eny.spi_plugin.sample.client

import java.util.ServiceLoader

import com.eny.spi_plugin.sample.spi.Greeting
import scala.collection.JavaConversions._

class Client(greeting:Greeting) {
  def run() = {
    println(greeting.greet())
  }
}

object Client {

  def main (args: Array[String]) =
    ServiceLoader
      .load(classOf[Greeting])
      .headOption
      .map {
        new Client(_).run()
      }
      .getOrElse({println ("Greeting implementation not found")})
}
