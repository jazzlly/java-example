package com.leetcode.tree.trie;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L208TrieTest {
    @Test
    public void smoke() {
        Trie trie = new Trie();

        trie.insert("apple");
        assertThat(trie.search("apple")).isTrue();
        assertThat(trie.search("app")).isFalse();
        assertThat(trie.startsWith("app")).isTrue();
        trie.insert("app");
        assertThat(trie.search("app")).isTrue();
    }

    @Test
    public void boundary() {
        Trie trie = new Trie();
        assertThat(trie.startsWith("")).isTrue();
        assertThat(trie.search("")).isFalse();

        trie.insert("");
        assertThat(trie.startsWith("")).isTrue();
        assertThat(trie.search("")).isTrue();
    }

    @Test
    public void smoke2() {
        Trie trie = new Trie();
        assertThat(trie.search("a")).isFalse();
    }
}