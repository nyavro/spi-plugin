##spi-plugin-sample

###Project demonstrating usage of spi-plugin


Consider multi-project with following sub-projects:

<ul>
<li>spi - contains interfaces and data types</li>
<li>impl - implementation of interfaces declared in spi project</li>
<li>spiClient - uses functionality of spi and not aware of spi implementation.</li>
</ul>

####Steps to make spiClient runtime depend on impl:
<ol>
  <li>
    Set exportJars to true at impl project:
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
  </li>
  <li>
    Enable spi-plugin at project impl:
      ```Scala
      impl
        ... // Other settings of project
        .enablePlugins(SpiPlugin)
      ```
  </li>
  <li>
     Configure keys of spi-plugin:
     *SpiKeys.spiPaths* - Set of directories to search for traits or interfaces
     *SpiKeys.implPaths* - Set of directories to search for implementations
     *SpiKeys.traits* - You can specify Set of desired interfaces to be exported.
       For example, when SpiKeys.traits set as Set("com.package.CorrespondenceService") then implementations of
        'com.package.CorrespondenceService', at paths specified by SpiKeys.implPaths will be exported
  </li>
  <li>
     Use mappings task to copy generated export files to "META-INF/services" folder of jar file
  </li>
  <li>
     Now make spiClient use impl in runtime by setting impl % "runtime" dependency
  </li>
</ol>