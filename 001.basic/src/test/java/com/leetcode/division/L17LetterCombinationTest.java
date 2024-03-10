package com.leetcode.division;


import org.junit.Test;

public class L17LetterCombinationTest {

    @Test
    public void smoke() {
        L17LetterCombination2 test = new L17LetterCombination2();
        System.out.println(test.letterCombinations(""));
        System.out.println(test.letterCombinations("98"));
//        System.out.println(test.reset().letterCombinations("23"));
    }
}