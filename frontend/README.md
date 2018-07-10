# How to install and use the ABS Compiler #

## Preliminaries ##

Compiling ABS needs [ant](https://ant.apache.org), the [Java
JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
version 8 or later, and [Erlang](http://www.erlang.org/downloads) version 20
or later.  If using the Maude backend, also install
[Maude](http://maude.cs.uiuc.edu/download/).

## Building the compiler ##

To build the compiler, run `make frontend` in the project root directory, or
`make` in this subdirectory.  You may need to set the following variable:

    export ANT_OPTS=-Xmx1g

A successful build generates the file `dist/absfrontend.jar` which contains
the compiler and runtime support files.  A convenience script called `absc`
that invokes the ABS compiler can be found in `bin/bash/` (for Unix-like
systems) and `bin/win` (for Windows).  For a list of options to the compiler,
see the output of `absc -help`.

## Checking ABS code ##

Invoke the ABS compiler with

    bin/bash/absc [options] <absfiles>

This will type-check the given input files and output a list of warnings and
errors.

## Using the Java/Scala backend ##

Note that the Scala backend is still in development right now.

Compile to Scala with

    bin/bash/absc -scala <absfiles>

This will generate a package for each module in ../generated-sources/jabsc/.
You can make this directory the root of an eclipse or Intellij (with Scala extension installed) project in which you have the API provided at
https://github.com/JaacRepo/JAAC.git

You should then be able to run the Main object from the module in which the main block was located
