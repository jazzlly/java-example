package com.java.examples.reflection;

/**
 * Created by jiangrui on 6/19/15.
 */
public class PackageName {

    public static void main(String[] args) {
        PackageName pname = new PackageName();

        System.out.println(pname.getClass().getPackage().getName());
    }
}
