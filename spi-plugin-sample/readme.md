##spi-plugin-sample

###Project demonstrating usage of spi-plugin


Consider multi-project with following sub-projects:

+ spi - contains interfaces and data types
+ impl - implementation of interfaces declared in spi project
+ spiClient - uses functionality of spi and not aware of spi implementation.

####Steps to make spiClient runtime depend on impl:
1.  Set exportJars to true at impl project:

    ```Scala
    lazy val impl:Project = Project(
      ...
      settings = ... ++ Seq(
        ...
        exportJars := true
        ...
      )
    )
    ```

2.  Enable spi-plugin at project impl:

    ```Scala
      impl
        ... // Other settings of project
        .enablePlugins(SpiPlugin)
    ```

3.  Configure keys of spi-plugin:

    + *SpiKeys.spiPaths* - Set of directories to search for traits or interfaces, in this case the set of one entry - path to spi project base
    + *SpiKeys.implPaths* - Set of directories to search for implementations, in this case the set of one entry - path to impl project base
4.  Use mappings task to copy generated export files to "META-INF/services" folder of jar file
5.  Now make spiClient use impl in runtime by setting impl % "runtime" dependency
