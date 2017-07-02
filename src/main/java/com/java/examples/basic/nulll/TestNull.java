package com.java.examples.basic.nulll;

/**
 * Created by jiangrui on 5/1/15.
 */
public class TestNull {

    public static void main(String[] args) {
        System.out.println(null instanceof String);
        System.out.println(getNull() instanceof String);
        System.out.println(getObject(null) instanceof String);
    }

    private static Object getObject(Object o) {
        return o;
    }

    public static Object getNull() {
        return null;
    }
}
