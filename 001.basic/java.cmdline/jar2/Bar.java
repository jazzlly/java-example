// package com.java.examples.classloader;

public class Bar {

    public int msg(String msg) {
        System.out.println("bar: " + msg);
        return 0;
    }

    public static void main(String[] args) {
        new Bar().msg("wahaha!");
    }
}
