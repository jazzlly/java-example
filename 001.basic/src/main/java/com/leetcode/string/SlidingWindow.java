package com.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    static public String minWindow(String source, String target) {
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

    static public boolean checkInclusion(String target, String source) {
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

            if (need.containsKey(c)) {
                window.put(c, 1 + window.getOrDefault(c, 0));
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while(right - left >= target.length()) {
                if (valid == need.size()) {
                    return true;
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

        return false;
    }

    static public List<Integer> findAnagrams(String source, String target) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < target.length(); i++) {
            need.put(target.charAt(i), 1 + need.getOrDefault(target.charAt(i), 0));
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < source.length()) {
            char c = source.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, 1 + window.getOrDefault(c, 0));
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while(right - left >= target.length()) {
                if (valid == need.size()) {
                    ans.add(left);
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

        return ans;
    }

    static public int lengthOfLongestSubstring(String source) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        int len = 0;

        while (right < source.length()) {
            char c = source.charAt(right);
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);
            if (window.get(c) == 1) {
                len = Math.max(len, right - left);
                continue;
            }

            while(window.get(c) > 1) {
                char c2 = source.charAt(left);
                left++;

                window.put(c2, window.get(c2) - 1);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        // assertThat(minWindow("ADOBECODEBANC", "ABC")).isEqualTo("BANC");
        // assertThat(minWindow("ADOBECODEBANC", "ABCD")).isEqualTo("ADOBEC");

        
        assertThat(checkInclusion("abcdxabcde", "abcdeabcdx")).isTrue();
    }
    
}
