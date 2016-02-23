package com.eny.plugin.spi

import java.io.File

import org.clapper.classutil.ClassFinder

/**
 * Represents interfaces (traits) of given path
 * @param path - path to search interfaces in
 */
class InterfacesList(path:String) {

  private val interfacesList = ClassFinder(new File(path) :: Nil)
    .getClasses()
    .withFilter(_.isInterface)
    .map(_.name)
    .toSet

  /**
   * @return Set of interfaces names
   */
  def interfaces:Set[String] = interfacesList
}