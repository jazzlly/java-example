package com.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class L242Anagram {

    // 方法1： 比较排序好的单词
    // 快排 nxlog(n)

    /**
     * 效率太差!
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagramHash2(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if ((s == null && t != null) ||
                (s != null && t == null)) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char c : s.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        return map1.equals(map2);
    }

    /**
     * 使用针对场景的简单的hash函数，比原生的hash函数要快
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagramHash(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if ((s == null && t != null) ||
                (s != null && t == null)) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }

        // 仅仅包括小写字母
        int array1[] = new int[26];
        int array2[] = new int[26];

        for (int i : array1) {
            array1[i] = 0;
            array2[i] = 0;
        }

        for (char c : s.toCharArray()) {
            array1[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            array2[c - 'a']++;
        }

        return Arrays.equals(array1, array2);
    }
}
