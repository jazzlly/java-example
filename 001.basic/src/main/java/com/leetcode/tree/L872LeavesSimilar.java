package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. 叶子相似的树
 * 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 * 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *
 * 提示：
 *
 * 给定的两颗树可能会有 1 到 200 个结点。
 * 给定的两颗树上的值介于 0 到 200 之间。
 */
public class L872LeavesSimilar {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        recursion(root1, list1);
        recursion(root2, list2);

        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void recursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }

        recursion(node.left, list);
        recursion(node.right, list);
    }
}
