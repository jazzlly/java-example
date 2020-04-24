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

    @Test
    public void deleteSmoke() {
        Trie trie = new Trie();

        trie.insert("a");
        trie.insert("ab");
        trie.insert("abc");

        trie.remove("ab");
        assertThat(trie.search("ab")).isFalse();
        assertThat(trie.startsWith("ab")).isTrue();
        assertThat(trie.search("abc")).isTrue();

        assertThat(trie.remove("abc")).isTrue();
        assertThat(trie.startsWith("ab")).isFalse();
        assertThat(trie.search("a")).isTrue();
        assertThat(trie.startsWith("a")).isTrue();

    }

    @Test
    public void b() {
        Trie trie = new Trie();
        assertThat(trie.childrenCount).isEqualTo(0);
        assertThat(trie.isEnd).isFalse();

        trie.insert("a");
        assertThat(trie.childrenCount).isEqualTo(1);
        assertThat(trie.isEnd).isFalse();

        assertThat(trie.remove("")).isFalse();
        assertThat(trie.remove("b")).isFalse();
        assertThat(trie.remove("a")).isTrue();

        assertThat(trie.childrenCount).isEqualTo(0);
        assertThat(trie.isEnd).isFalse();

        trie.insert("a");
        trie.insert("ab");
        trie.insert("abc");

        trie.remove("ab");
        assertThat(trie.search("ab")).isFalse();
        assertThat(trie.search("abc")).isTrue();



    }
}