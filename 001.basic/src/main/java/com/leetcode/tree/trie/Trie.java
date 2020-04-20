package com.leetcode.tree.trie;

/**
 * Trie
 *
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class Trie {

    /**
     * 26个子节点连接
     */
    final Trie[] children;

    /**
     * 是否是叶子节点
     */
    boolean isLeafNode;

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isLeafNode = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        assert word != null;

        Trie tmp = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (tmp.children[c - 'a'] == null) {
                tmp.children[c - 'a'] = new Trie();
            }
            tmp = tmp.children[c - 'a'];
        }
        tmp.isLeafNode = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie trie = startWithInner(word);
        return trie != null ? trie.isLeafNode : false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startWithInner(prefix) != null;
    }

    Trie startWithInner(String prefix) {
        assert prefix != null;

        Trie tmp = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            tmp = tmp.children[c - 'a'];
            if (tmp == null) {
                return null;
            }
        }
        return tmp;
    }
}
