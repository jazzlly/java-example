// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "com_pekall_HelloJNI.h"   // Generated

// Implementation of the native method sayHello()
JNIEXPORT void JNICALL Java_com_pekall_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World! 哈哈哈\n");
   return;
}