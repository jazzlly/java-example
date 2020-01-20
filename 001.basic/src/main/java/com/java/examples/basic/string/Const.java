package com.java.examples.basic.string;

/**
 * Created by jiangrui on 5/1/15.
 */
public class Const {
    public static void main(String[] args) {
        // testConst1();

        String a = "a";
        final String c = "a";

        // a could be changed by maybe aop, so compile can not optimize it
        String b = a + "b";

        // c could not be changed, so compiler can optimize it
        String d = c + "b";
        String e = getA() + "b";

        String compare = "ab";
        System.out.println(b == compare);
        System.out.println(d == compare);
        System.out.println(e == compare);

    }

    private static void testConst1() {
        String a = "a" + "b" + 1;
        String b = "ab1";
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    public static String getA() {
        return "a";
    }
}
