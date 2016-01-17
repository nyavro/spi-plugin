package com.eny.plugin.spi

import java.io.File

import org.clapper.classutil.ClassFinder

class InterfacesList(path:String) {

  def interfaces = ClassFinder(new File(path) :: Nil)
    .getClasses()
    .withFilter(_.isInterface)
    .map(_.name)
    .toSet
}