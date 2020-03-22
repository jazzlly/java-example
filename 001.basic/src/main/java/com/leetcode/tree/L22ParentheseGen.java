package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class L22ParentheseGen {

    List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return result;
        }

        dfs(n, n, "");
        return result;
    }

    void dfs(int left, int right, String tmp) {
        if (left == 0 && right == 0) {
            result.add(tmp);
            return;
        }

        if (left > 0) {
            dfs(left - 1, right, tmp + "(");
        }
        if (right > 0 && right > left) {
            dfs(left, right - 1, tmp + ")");
        }
    }
}
