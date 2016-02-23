package com.eny.plugin.spi

import java.io.File

import org.clapper.classutil.ClassFinder

/**
 * Represents list of implementations of given interfaces(traits) in given paths
 * @param interfaces - Set of interfaces to find implementations of
 * @param paths - Collection of paths to search implementations in
 */
class ImplementationsList(interfaces:Set[String], paths:Seq[String]) {

  private val foundImplementations = ClassFinder(paths.map(new File(_)))
    .getClasses()
    .withFilter(item => item.isConcrete && interfaces.exists(item.implements))
    .map(_.name)
    .toSet

  /**
   * Collection of implementations of interfaces in paths
   * @return Set of class names
   */
  def implementations():Set[String] = foundImplementations

}
