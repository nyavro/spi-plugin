package com.eny.plugin.spi

import java.io.File

import org.clapper.classutil.ClassFinder

class ImplementationsList(interfaces:Set[String], paths:Seq[String]) {
  def implementations() =
    ClassFinder(paths.map(new File(_)))
      .getClasses()
      .withFilter(item => item.isConcrete && interfaces.exists(item.implements))
      .map(_.name)
      .toSet
}
