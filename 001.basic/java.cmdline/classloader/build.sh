#!/usr/bin/env bash

javac Foo.java Bar.java

buildWithMain() {
  jar -cvfm foo.jar META-INF/MANIFEST.MF Foo.class Bar.class
  java -jar foo.jar
}

buildWithoutMain() {
  jar -cvf foo.jar Foo.class Bar.class
  java -classpath foo.jar Foo
  java -classpath foo.jar Bar
}

buildWithoutMain
