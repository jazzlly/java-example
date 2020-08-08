package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

/**
 * 1120. 子树的最大平均值 给你一棵二叉树的根节点 root，找出这棵树的 每一棵 子树的 平均值 中的 最大 值。
 *
 * 子树是树中的任意节点和它的所有后代构成的集合。
 *
 * 树的平均值是树中节点值的总和除以节点数。
 */
public class L1120MaxiumAvg {

    double maxAvg = Integer.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        recurse(root);
        return maxAvg;
    }

    private Result recurse(TreeNode node) {
        if (node == null) {
            return new Result(0, 0);
        }

        Result left = recurse(node.left);
        Result right = recurse(node.right);
        
        Result result = new Result(left.nodeCnt + right.nodeCnt + 1, 
            left.total + right.total + node.val);

        maxAvg = Math.max(maxAvg, result.total/(double)result.nodeCnt);
        return result;
    }

    class Result {
        int nodeCnt;
        int total;

        public Result(int nodeCnt, int total) {
            this.nodeCnt = nodeCnt;
            this.total = total;
        }
    }

    public static void main(String[] args) {
        L1120MaxiumAvg test = new L1120MaxiumAvg();
        double avg = test.maximumAverageSubtree(TreeUtils.makeBinaryTree(5, 6, 1));
        System.out.println(avg);
    }
}
