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

    static public String minWindow1(String source, String target) {
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

    public String minWindow(String source, String target) {
        int[] need = new int[128];
        int[] window = new int[128];
        int uniqCnt = 0;
        
        for (int i = 0; i < target.length(); i++) {
            if (need[target.charAt(i)] == 0) {
                uniqCnt++;
            }
            need[target.charAt(i)]++;
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < source.length()) {
            char c = source.charAt(right);
            right++;

            if (need[c] > 0) {
                window[c]++;
                if (window[c] == need[c]) {
                    valid++;
                }
            }

            while(valid >= uniqCnt) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char c2 = source.charAt(left);
                left++;

                if (need[c2] > 0) {
                    if (window[c2] == (need[c2])) {
                        valid--;
                    }
                    window[c2]--;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : source.substring(start, start + len);
    }

    static public boolean checkInclusion1(String target, String source) {
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

    static public boolean checkInclusion(String target, String source) {
        int[] need = new int[128];
        int[] window = new int[128];
        int uniqCnt = 0;
        
        for (int i = 0; i < target.length(); i++) {
            if (need[target.charAt(i)] == 0) {
                uniqCnt++;
            }
            need[target.charAt(i)]++;
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < source.length()) {
            char c = source.charAt(right);
            right++;

            if (need[c] > 0) {
                window[c]++;
                if (window[c] == need[c]) {
                    valid++;
                }
            }

            while(right - left >= target.length()) {
                if (valid == uniqCnt) {
                    return true;
                }

                char c2 = source.charAt(left);
                left++;

                if (need[c2] > 0) {
                    if (window[c2] == need[c2]) {
                        valid--;
                    }
                    window[c2]--;
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

        
        // assertThat(checkInclusion("abcdxabcde", "abcdeabcdx")).isTrue();

        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');
        StringBuilder builder = new StringBuilder();
        for (int i = 65; i < 123; i++) {
            builder.append((char)i);
        }
        System.out.println(builder.toString());
    }
    
}
