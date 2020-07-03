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
 *
 * 本题的思路：
 *  1. 从左向右放括号
 *  2. 只要还有左括号，就能放左括号
 *  3. 有右括号，而且剩余右括号大于剩余左括号，则还能放右括号
 */
public class L22ParentheseGen {

    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return ans;
        }

        recursion("", 0, 0, n);
        return ans;
    }


    void recursion(String current, int leftCnt, int rightCnt, int n) {
        if (leftCnt == n && rightCnt == n) {
            ans.add(current);
            return;
        }

        if (leftCnt < n) {
            recursion(current + "(", leftCnt + 1, rightCnt, n);
        }
        if (rightCnt < leftCnt) {
            recursion(current + ")", leftCnt, rightCnt + 1, n);
        }
    }































}
