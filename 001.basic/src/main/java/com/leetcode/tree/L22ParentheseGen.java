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

        dfsTest(n, n, "");
        return result;
    }

    private void dfsTest(int leftRemain, int rightRemain, String s) {
        if (leftRemain == 0 && rightRemain == 0) {
            result.add(s);
            return;
        }

        if (leftRemain > 0) {
            dfsTest(leftRemain - 1, rightRemain, s + "(");
        }
        if (rightRemain > 0 && rightRemain > leftRemain) {
            dfsTest(leftRemain, rightRemain - 1, s + ")");
        }
    }

}
