package com.leetcode.tree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */
public class L98ValidBSTTest {

    /*
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     */
    @Test
    public void smoke() {
        L98ValidBST bst = new L98ValidBST();
        TreeNode root = new TreeNode(2);
        bst.appendLeftChild(root, new TreeNode(1));
        bst.appendRightChild(root, new TreeNode(3));

        assertThat(bst.isValidBST3(root)).isTrue();
    }

    /*
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     */
    @Test
    public void smoke2() {
        L98ValidBST bst = new L98ValidBST();
        TreeNode root = new TreeNode(5);
        TreeNode l11 = new TreeNode(1);
        TreeNode l12 = new TreeNode(4);
        TreeNode l21 = new TreeNode(3);
        TreeNode l22 = new TreeNode(6);

        bst.appendLeftChild(root, l11);
        bst.appendRightChild(root, l12);
        bst.appendLeftChild(l12, l21);
        bst.appendRightChild(l12, l22);

        assertThat(bst.isValidBST3(root)).isFalse();
    }
}