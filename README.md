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

### Licence

MIT License

Copyright (c) [2018] [Evgeniy Nyavro]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
