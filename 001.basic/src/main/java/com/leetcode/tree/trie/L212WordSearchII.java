package com.leetcode.tree.trie;

import java.util.*;

/**
 * 212. 单词搜索 II
 * 给定一个二维网格 board 和一个字典中的单词列表 words，
 * 找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些
 * 水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行
 * 这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，
 * 请先查看这个问题： 实现Trie（前缀树）。
 */
public class L212WordSearchII {

    Trie trie = new Trie();
    char[][] board;
    boolean[][] flags;
    Set<String> result = new HashSet<>();

    int[][] offset = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    int wordMaxLen = -1;

    /*
    1. 将words加入到字典树中
    2. 对board进行dfs
     */
    public List<String> findWords(char[][] board, String[] words) {
        assert board != null;
        assert words != null;
        if (words.length == 0) {
            return Collections.emptyList();
        }

        this.board = board;
        flags = new boolean[board.length][board[0].length];
        for (String word : words) {
            trie.insert(word);
            wordMaxLen = Math.max(wordMaxLen, word.length());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                flags[i][j] = true;
                dfs(i, j, "" + board[i][j]);
                flags[i][j] = false;
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(int i, int j, String current) {
        if (!trie.startsWith(current)) {
            return;
        }

        if (trie.search(current)) {
            result.add(current);
            // 剪枝优化：当前结果长度已经达到字典单词的最大长度
            if (current.length() >= wordMaxLen) {
                return;
            }
        }

        for (int k = 0; k < offset.length; k++) {
            int row = i + offset[k][0];
            int col = j + offset[k][1];
            Character tmp = getChar(row, col);
            if (tmp == null) {
                continue;
            }

            if (flags[row][col]) {
                continue;
            }
            flags[row][col] = true;
            dfs(row, col, current + tmp);
            flags[row][col] = false;
        }
    }

    Character getChar(int i, int j) {
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
            return board[i][j];
        }
        return null;
    }

    class Trie {

        /**
         * 26个子节点连接
         */
        Trie[] children;

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

}
