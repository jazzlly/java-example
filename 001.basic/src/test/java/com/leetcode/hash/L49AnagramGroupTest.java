package com.leetcode.hash;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void boudary() {
        List<List<String >> lists = new L49AnagramGroup().groupAnagrams(new String[0]);
        assertThat(lists).hasSize(0);
    }

    @Test
    public void arrayTest() {
        int[] array1 = new int[26];
        int[] array2 = new int[26];

        assertThat(Arrays.equals(array1, array2)).isTrue();

        array1[2] = 8;
        array2[2] = 8;
        assertThat(Arrays.equals(array1, array2)).isTrue();
    }
}