package com.eny.plugin.spi

class MapImplementations(spiPaths: Seq[String], traits: Seq[String], implPath: Seq[String]) {
  def run() = {
    (spiPaths.flatMap(new InterfacesList(_).interfaces) ++ traits)
      .map(item => item -> new ImplementationsList(Set(item), implPath).implementations())
      .toMap
  }
}
