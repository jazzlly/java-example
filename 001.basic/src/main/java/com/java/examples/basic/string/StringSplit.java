
package com.java.examples.basic.string;

import java.util.Arrays;

public class StringSplit {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] classIds = "12345".split(",");
        System.out.println(classIds.length);
        System.out.println(Arrays.toString(classIds));

//        String sentence = ";long1;long2;long3;long4;";
//        String[] tokens = sentence.split(";");
//        for (String str : tokens) {
//            System.out.println("Token: " + str);
//        }
    }
}
