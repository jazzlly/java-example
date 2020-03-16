package com.leetcode.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class L49AnagramGroup {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, int[]> tableMap = new HashMap<>();

        // todo:
        return null;
    }

    public int[] computeHashTable(String str) {
        int[] table = new int[26];
        for (char c : str.toCharArray()) {
            table[c - 'a']++;
        }

        return table;
    }
}
