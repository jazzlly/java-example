package com.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 *
 *
 * 案例 2:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * 输出: False
 *
 */
public class L653TwoSumBst {

    int target = 0;
    boolean found = false;
    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        target = k;
        recursion(root);
        return found;
    }

    private void recursion(TreeNode node) {
        if (node == null || found) {
            return;
        }

        if (set.contains(target - node.val)) {
            found = true;
            return;
        }
        set.add(target - node.val);

        recursion(node.left);
        recursion(node.right);
    }
}
