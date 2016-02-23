package com.eny.plugin.spi

/**
 * Represents correspondence between interface name and its implementations
 * @param spiPaths - Optional Collection of interfaces (traits) packages
 * @param traits - Optional collection of interfaces (traits) names
 * @param implPath - Collection of paths to implementations packages
 */
class MapImplementations(spiPaths: Seq[String], traits: Seq[String], implPath: Seq[String]) {

  /**
   * Example output:
   *   Map (
   *     "some.package.traitA" -> Set("some.impl.AImpl", "some.impl2.AImpl2"),
   *     "some.package.traitB" -> Set("some.impl.BImpl", "some.impl2.BImpl2")
   *   )
   * @return Map of interface name to collection of it's implementation class names
   */
  def run() = {
    (spiPaths.flatMap(new InterfacesList(_).interfaces) ++ traits)
      .map(item => item -> new ImplementationsList(Set(item), implPath).implementations())
      .toMap
  }
}
