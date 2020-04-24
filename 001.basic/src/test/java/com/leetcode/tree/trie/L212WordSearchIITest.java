package com.leetcode.tree.trie;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class L212WordSearchIITest {
    /*
     * words = ["oath","pea","eat","rain"] and board =
     * [
     *   ['o','a','a','n'],
     *   ['e','t','a','e'],
     *   ['i','h','k','r'],
     *   ['i','f','l','v']
     * ]
     *
     * 输出: ["eat","oath"]
     */

    @Test
    public void smoke() {

        L212WordSearchII test = new L212WordSearchII();
        List<String> result = test.findWords(new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"});
        assertThat(result).containsExactlyInAnyOrder("eat", "oath");
    }

    @Test
    public void smoke2() {

        L212WordSearchII test = new L212WordSearchII();
        List<String> result = test.findWords(
                new char[][]{{'a'}}, new String[]{"a"});
        assertThat(result).containsExactlyInAnyOrder("a");
    }


    @Test
    public void smoke3() {

        L212WordSearchII test = new L212WordSearchII();
        List<String> result = test.findWords(new char[][]{
                        {'a', 'b'},
                        {'a', 'a'}},
                new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"});
        System.out.println(result);
        assertThat(result).containsExactlyInAnyOrder(
                "aaa","aaab","aaba","aba","baa");
        // ["aaa","aaab","aaba","aba","baa"]
        // [, , , , ]
    }

    @Test
    public void smoke4() {

        L212WordSearchII test = new L212WordSearchII();
        List<String> result = test.findWords(new char[][]{
                        {'a', 'b'},
                        {'a', 'a'}},
                new String[]{"aaba"});
        assertThat(result).containsExactlyInAnyOrder(
                "aaba");
    }

    @Test
    public void smoke5() {

        L212WordSearchII test = new L212WordSearchII();
        List<String> result = test.findWords(new char[][] {
                                {'b','a','a','b','a','b'},
                                {'a','b','a','a','a','a'},
                                {'a','b','a','a','a','b'},
                                {'a','b','a','b','b','a'},
                                {'a','a','b','b','a','b'},
                                {'a','a','b','b','b','a'},
                                {'a','a','b','a','a','b'}
                        },
                // new String[]{"aabbbbabbaababaaaabababbaaba"});
                new String[]{"aabbbbabbaababaaaabababb"});
        assertThat(result).containsExactlyInAnyOrder(
                "aabbbbabbaababaaaabababb");
                // "aabbbbabbaababaaaabababbaaba");
    }

}