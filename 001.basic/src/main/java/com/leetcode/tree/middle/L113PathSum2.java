package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class L113PathSum2 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return ans;
        }

        dfs(root, new ArrayList<>(), sum);
        return ans;
    }

    void dfs(TreeNode node, List<Integer> pathVal, int sum) {
        pathVal.add(node.val);
        sum -= node.val;

        // leaf node
        if (node.left == null && node.right == null) {
            if (0 == sum) {
                ans.add(new ArrayList<>(pathVal));
            }
        }

        if (node.left != null) {
            dfs(node.left, pathVal, sum);
        }
        if (node.right != null) {
            dfs(node.right, pathVal, sum);
        }

        pathVal.remove(pathVal.size() - 1);
    }

    public static void main(String[] args) {
        L113PathSum2 test = new L113PathSum2();
        TreeNode root =TreeUtils.makeBinaryTree(Arrays.asList(
                5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1));
        List<List<Integer>> ans = test.pathSum(root, 22);
        System.out.println(ans);
    }
}
