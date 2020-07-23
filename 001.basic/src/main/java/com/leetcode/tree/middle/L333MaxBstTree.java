package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.Map;

/**
 * 333. 最大 BST 子树
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，其中最大指的是子树节点数最多的。
 *
 * 注意:
 * 子树必须包含其所有后代。
 *
 * 示例:
 *
 * 输入: [10,5,15,1,8,null,7]
 *
 *    10
 *    / \
 *   5  15
 *  / \   \
 * 1   8   7
 *
 * 输出: 3
 * 解释: 高亮部分为最大的 BST 子树。
 *      返回值 3 在这个样例中为子树大小。
 * 进阶:
 * 你能想出用 O(n) 的时间复杂度解决这个问题吗？
 */
public class L333MaxBstTree {
    int maxBstCnt = 0;

    public int largestBSTSubtree(TreeNode root) {
        recursion(root);
        return maxBstCnt;
    }

    private SubTreeInfo recursion(TreeNode node) {
        if (node == null) {
            return new SubTreeInfo(true, Integer.MIN_VALUE,
                    Integer.MAX_VALUE, 0);
        }

        SubTreeInfo left = recursion(node.left);
        SubTreeInfo right = recursion(node.right);

        SubTreeInfo info = new SubTreeInfo(false,
                Math.max(node.val, Math.max(left.maxVal, right.maxVal)),
                Math.min(node.val, Math.min(left.minVal, right.minVal)),
                1 + left.nodeCnt + right.nodeCnt);
        if (!left.isBst || !right.isBst ||
                (left.nodeCnt > 0 && left.maxVal >= node.val) ||
                (right.nodeCnt > 0 && right.minVal <= node.val)) {
            return info;
        }

        info.setBst(true);
        maxBstCnt = Math.max(maxBstCnt, info.nodeCnt);
        return info;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeBinaryTree(
                3,2,4,null,null,1);
        L333MaxBstTree test = new L333MaxBstTree();
        int cnt = test.largestBSTSubtree(root);

        System.out.println(cnt);
    }

    /**
     * 递归函数返回中间信息
     */
    class SubTreeInfo {
        /**
         * 子树是否为bst
         */
        boolean isBst;
        /**
         * 子树节点最大值
         */
        int maxVal;
        /**
         * 子树最小值
         */
        int minVal;
        /**
         * 子树节点数
         */
        int nodeCnt;

        public SubTreeInfo(boolean isBst, int maxVal, int minVal, int nodeCnt) {
            this.isBst = isBst;
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.nodeCnt = nodeCnt;
        }

        public void setBst(boolean bst) {
            isBst = bst;
        }
    }
}

