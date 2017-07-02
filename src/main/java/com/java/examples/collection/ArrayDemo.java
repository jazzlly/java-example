package com.java.examples.collection;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.Arrays;

/**
 * Created by jiangrui on 7/4/15.
 */
public class ArrayDemo {

    public static void main(String[] args) {
        String test[] = new String[] {"1", "fjdk", "3434", "8789"};
        String test2[] = {"1", "fjdk", "3434", "8789"};

        String copy[] = Arrays.copyOf(test, test.length);
        copy[0] = "3";

        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(copy));

        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));


    }
}
