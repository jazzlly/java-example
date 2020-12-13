package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SlidingWindow {

    void template(String source, String target) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < target.length(); i++) {
            need.put(target.charAt(i), 1 + need.getOrDefault(target.charAt(i), 0));
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < source.length()) {
            char c = source.charAt(right);
            right++;
            // todo: 

            while(true) {
                char c2 = source.charAt(left);
                left++;
            }
            
        }
    }

    static String minWindow(String source, String target) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < target.length(); i++) {
            need.put(target.charAt(i), 1 + need.getOrDefault(target.charAt(i), 0));
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < source.length()) {
            char c = source.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, 1 + window.getOrDefault(c, 0));
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while(valid >= need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char c2 = source.charAt(left);
                left++;

                if (need.containsKey(c2)) {
                    if (window.get(c2).equals(need.get(c2))) {
                        valid--;
                    }
                    window.put(c2, window.get(c2) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : source.substring(start, start + len);

    }

    public static void main(String[] args) {
        assertThat(minWindow("ADOBECODEBANC", "ABC")).isEqualTo("BANC");
        assertThat(minWindow("ADOBECODEBANC", "ABCD")).isEqualTo("ADOBEC");
    }
    
}
