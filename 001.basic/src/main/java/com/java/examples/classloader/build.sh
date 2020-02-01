#!/usr/bin/env bash

javac Foo.java Bar.java
# jar -cvf foo.jar Foo.class Bar.class
jar -cvfm foo.jar META-INF/MANIFEST.MF Foo.class Bar.class

java -jar foo.jar
