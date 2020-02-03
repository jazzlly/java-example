#!/usr/bin/env bash

# 制作一个带package的jar包
mkdir -p ./target

javac Bar.java com/Foo.java -d ./target

# 运行.class文件
java -classpath ./target com.Foo
java -classpath ./target Bar

buildWithMain() {
  jar -cvfm foo.jar META-INF/MANIFEST.MF Foo.class Bar.class
  java -jar foo.jar
}

# 制作jar包
buildWithoutMain() {
  jar cvf foo.jar -C target/ .
  java -classpath foo.jar com.Foo
  java -classpath foo.jar Bar
}

buildWithoutMain
