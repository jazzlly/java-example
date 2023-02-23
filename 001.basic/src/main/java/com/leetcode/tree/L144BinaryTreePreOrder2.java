package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class L144BinaryTreePreOrder2 {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (p != null) {
                stack.push(p);
                result.add(p.val);
                p = p.left;
                continue;
            }

            if (stack.empty()) {
                break;
            }

            p = stack.pop().right;
        }

        return result;
    }
}

