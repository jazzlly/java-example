package com.leetcode.array;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class L1002CommonChars {

    public List<String> commonChars(String[] A) {
        if (A == null || A.length == 0) {
            return Collections.emptyList();
        }

        int[] minFreqs = new int[26];
        Arrays.fill(minFreqs, Integer.MAX_VALUE);

        for (String s : A) {
            int[] freqs = new int[26];
            for (int j = 0; j < s.length(); j++) {
                freqs[s.charAt(j) - 'a']++;
            }
            for (int j = 0; j < minFreqs.length; j++) {
                minFreqs[j] = Math.min(minFreqs[j], freqs[j]);
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < minFreqs.length; i++) {
            for (int j = 0; j < minFreqs[i]; j++) {
                list.add(String.valueOf((char) ('a' + i)));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        L1002CommonChars test = new L1002CommonChars();
        System.out.println(test.commonChars(null));
        System.out.println(test.commonChars(new String[]{}));
        System.out.println(test.commonChars(new String[]{""}));
        System.out.println(test.commonChars(new String[]{"l"}));
        System.out.println(test.commonChars(new String[]{"bella"}));
        System.out.println(test.commonChars(new String[]{"bella","label","roller"}));
        System.out.println(test.commonChars(new String[]{"cool","lock","cook"}));
    }
}
