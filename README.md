# spi-plugin

![Build Status](https://travis-ci.org/nyavro/spi-plugin.svg?branch=master)

**spi-plugin** helps automatically generate *META-INF/services/** for use by java.util.ServiceLoader

### Configuring
**spi-plugin** has the following settings:

+ *SpiKeys.spiPaths* - Set of paths to search for traits or interfaces. Path may point to jar file, zip file or directory
+ *SpiKeys.implPaths* - Set of directories to search for implementations
+ *SpiKeys.traits* - 'manually' point the set of desired interfaces to be exported.
   For example, when SpiKeys.traits set as Set("com.package.CorrespondenceService") then implementations of
   'com.package.CorrespondenceService', at paths specified by SpiKeys.implPaths will be exported
+ *SpiKeys.spiModules* - You can pass a Set of ModuleID to search for traits

### How does it work?

For each interface found by *SpiKeys.spiPaths* or *SpiKeys.traits* and having implementations
found in *SpiKeys.implPaths* **spi-plugin's** task *mapExport* generates a file which name
is the name of the interface and its content is the list of found implementations.
After that, using standard sbt task *mappings* these files gets copied into *META-INF/services/*
folder inside target jar.

See [spi-plugin-sample](https://github.com/nyavro/spi-plugin/tree/master/spi-plugin-sample) project

