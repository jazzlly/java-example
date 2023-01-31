package com.java.examples.object;

import org.openjdk.jol.vm.VM;

public class ObjectLayout {

    public static void main(String[] args) {
        String str = "hello";
        String str1 = "hello";

        System.out.println("address: " + VM.current().addressOf(str));
        System.out.println("address: " + VM.current().addressOf(str1));
        System.out.println("hash: " + str.hashCode());
        System.out.println("hash: " + str1.hashCode());
        System.out.println("system hash: " + System.identityHashCode(str));
        System.out.println("system hash: " + System.identityHashCode(str1));
    }
}
