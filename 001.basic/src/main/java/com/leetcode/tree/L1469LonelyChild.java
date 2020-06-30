package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 1469. 寻找所有的独生节点
 * 二叉树中，如果一个节点是其父节点的唯一子节点，则称这样的节点为 “独生节点” 。
 * 二叉树的根节点不会是独生节点，因为它没有父节点。
 *
 * 给定一棵二叉树的根节点 root ，返回树中 所有的独生节点的值所构成的数组 。数组的顺序 不限 。
 */
public class L1469LonelyChild {
    List<Integer> answer = new ArrayList<>();

    public List<Integer> getLonelyNodes(TreeNode root) {
        recursion(root);
        return answer;
    }

    private void recursion(TreeNode node) {
        if (node == null ||
                (node.left == null && node.right == null)) {
            return;
        }

        if (node.left != null && node.right != null) {
            recursion(node.left);
            recursion(node.right);
        }

        if (node.left == null) {
            answer.add(node.right.val);
            recursion(node.right);
        }
        if (node.right == null) {
            answer.add(node.left.val);
            recursion(node.left);
        }
    }
}
