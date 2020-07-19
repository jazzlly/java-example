package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.Arrays;

/**
 * 250. 统计同值子树
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 *
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 *
 * 示例：
 *
 * 输入: root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * 输出: 4
 */
public class L250SameValSubTree {

    int ans = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root != null) {
            dfs(root);
        }

        return ans;
    }

    // 1. 如果是叶子节点，则为满足条件
    // 2. 如果左，右子树为同值子树，且左右值和根的值相等
    Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(true, null);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);
        if (!left.result || !right.result) {
            return new Result(false, null);
        }

        if ((left.val != null && !left.val.equals(node.val)) ||
                (right.val != null && !right.val.equals(node.val)) ) {
            return new Result(false, null);
        }

        ans++;
        return new Result(true, node.val);
    }

    class Result {
        boolean result;
        Integer val;

        public Result(boolean result, Integer val) {
            this.result = result;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        L250SameValSubTree test = new L250SameValSubTree();
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                5,1,5,5,5,null,5));

        System.out.println(test.countUnivalSubtrees(root));

    }
}
