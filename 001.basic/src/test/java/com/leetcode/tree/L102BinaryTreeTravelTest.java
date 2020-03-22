package com.leetcode.tree;


import org.junit.Test;

public class L102BinaryTreeTravelTest {
    /*
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    @Test
    public void smoke() {
        TreeNode root = new TreeNode(3);
        TreeNode l11 = new TreeNode(9);
        TreeNode l12 = new TreeNode(20);
        TreeNode l21 = new TreeNode(15);
        TreeNode l22 = new TreeNode(7);

        root.left = l11;
        root.right = l12;
        l12.left = l21;
        l12.right = l22;

        System.out.println(new L102BinaryTreeTravel().levelOrder(root));

        // new L102BinaryTreeTravel().dfs(root);

        // assertThat(new L102BinaryTreeTravel().levelOrder(root))
           //     .containsExactly(3, 9, 20, 15, 7);
    }
}