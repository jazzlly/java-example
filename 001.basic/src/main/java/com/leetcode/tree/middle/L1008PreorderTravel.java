package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;

/**
 * 1008. 先序遍历构造二叉树
 * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 *
 * (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，
 * 值总 < node.val，而 node.right 的任何后代，值总 > node.val。
 * 此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）
 *
 *
 *
 * 示例：
 *
 * 输入：[8,5,1,7,10,12]
 * 输出：[8,5,10,1,7,null,12]
 */
public class L1008PreorderTravel {

    public TreeNode bstFromPreorder(int[] preorder) {
        return recursion(preorder, 0, preorder.length);
    }

    /**
     *
     * @param nums
     * @param begin inclusive
     * @param end exclusive
     * @return
     */
    TreeNode recursion(int[] nums, int begin, int end) {
        if (begin < 0 || end > nums.length || begin >= end ) {
            return null;
        }

        TreeNode node = new TreeNode(nums[begin]);

        // split [begin + 1, end) to left tree and right tree
        int leftEndIndex = begin + 1;
        while (leftEndIndex < end && nums[leftEndIndex] < nums[begin]) {
            leftEndIndex ++;
        }

        node.left = recursion(nums, begin + 1, leftEndIndex);
        node.right = recursion(nums, leftEndIndex , end);

        return node;
    }

    public static void main(String[] args) {
        L1008PreorderTravel test = new L1008PreorderTravel();
        TreeNode root = test.bstFromPreorder(new int[] {8,5,1,7,10,12});

        System.out.println("haha");
    }
}
