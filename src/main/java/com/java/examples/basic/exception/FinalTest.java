package com.java.examples.basic.exception;

/**
 * Created by jiangrui on 7/13/15.
 */
public class FinalTest {

    public static void main(String[] args) {
        int a = 10;

        if (a == 11) {
            System.out.println("return before try");
            return;
        }

        try {
            a = 30;
            System.out.println("return in try block");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("enter finally!");
        }
    }
}
