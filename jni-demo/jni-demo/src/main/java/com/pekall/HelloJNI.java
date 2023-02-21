package com.pekall;

/**
 * 编译流程:
 * cd jni-demo/jni-demo/src/main/java
 * javac -h ./com/pekall/ ./com/pekall/HelloJNI.java
 * gcc -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -shared -o libhello.so ./com/pekall/HelloJNI.c
 * cp -rf com/pekall/libhello.so /usr/lib
 * java com.pekall.HelloJNI
 *
 */
public class HelloJNI
{
    static {
        // System.loadLibrary("libhello.so");
        System.load("/usr/lib64/libhello.so");
        // Load native library hello.dll (Windows) or libhello.so (Unixes)
        //  at runtime
        // This library contains a native method called sayHello()
    }

    // Declare an instance native method sayHello() which receives no parameter and returns void
    private native void sayHello();

    // Test Driver
    public static void main(String[] args) {
        String libPath = System.getProperty("java.library.path");
        System.out.println("java.library.path=" + libPath);

        new HelloJNI().sayHello();  // Create an instance and invoke the native method
    }
}
