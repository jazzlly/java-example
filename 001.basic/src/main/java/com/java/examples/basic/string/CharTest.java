package com.java.examples.basic.string;

/**
 * Created by jiangrui on 6/22/15.
 */
public class CharTest {
    public static void main(String[] args) {
        String test = "hello姜睿!Ωå\ufffff Ω≈åœ∑´†¥¡™£¢∞§¶•ªº–≠œ∑´†¥¨ˆπ“‘«åß∂ƒ©∫˚µ¬Ω≈ç√∫˜˜µ≤≥µæ";
        System.out.println("is sup? " + Character.isSupplementaryCodePoint(0x10ffd));

        for (int i = 0; i < test.length(); i++) {
            System.out.println(test.charAt(i));
        }
        for (int i = 0; i < test.length(); i++) {
            int cp = test.codePointAt(i);

            System.out.println("cp is: " + cp);
            System.out.println("is sup? " + Character.isSupplementaryCodePoint(cp));
        }

        int cpCnt = test.codePointCount(0, test.length());
        System.out.println("code point count: " + cpCnt);
    }
}
