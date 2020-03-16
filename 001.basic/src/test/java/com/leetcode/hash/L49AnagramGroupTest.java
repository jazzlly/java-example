package com.leetcode.hash;

import org.junit.Test;

import java.util.List;

public class L49AnagramGroupTest {

    /**
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     */
    @Test
    public void smoke() {
        List<List<String >> lists = new L49AnagramGroup().groupAnagrams(
                new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        lists.forEach(strings -> {
            System.out.println(strings);
        });
    }
}