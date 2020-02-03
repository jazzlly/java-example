#!/usr/bin/env bash

# 制作一个带package的jar包
rm -rf ./target ./target2
mkdir -p ./target ./target2

# 编译java文件
javac Bar.java com/Foo.java -d ./target

# 创建两个资源文件
echo '你好，abc，娃哈哈' > target/text.txt
echo '你好，abc，娃哈哈' >> target/text.txt
mkdir -p target/res
echo '娃哈哈' >> target/res/file.txt

# 运行.class文件
java -classpath ./target com.Foo
java -classpath ./target Bar

buildWithMain() {
  jar -cvfm foo.jar META-INF/MANIFEST.MF Foo.class Bar.class
  java -jar foo.jar
}

# 制作jar包, 将class文件和资源文件打入jar包
buildWithoutMain() {
  jar cvf foo.jar -C target/ .
  java -classpath foo.jar com.Foo
  java -classpath foo.jar Bar
}

buildWithoutMain

javac -cp foo.jar Wahaha.java -d target2
jar cvf bar.jar -C ./target2 .

# 报错class not found
java -classpath bar.jar Wahaha

java -verbose -classpath bar.jar:foo.jar Wahaha
