package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.Arrays;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class L106BuildTreeInOrderAndPostOrder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return recursion(inorder, 0, inorder.length,
                postorder, 0, postorder.length);
    }

    /**
     * 通过中序和后序数组递归建立二叉树
     * @param inorder 中序数组
     * @param inBegin 中序数组开始index, inclusive
     * @param inEnd 中序数组结束index, exclusive
     * @param postorder 后序数组
     * @param postBegin 后序数组开始index, inclusive
     * @param postEnd 后续数组结束index, exclusive
     * @return 二叉树根节点
     */
    TreeNode recursion(int[] inorder, int inBegin, int inEnd,
                       int[] postorder, int postBegin, int postEnd) {
        if (inBegin >= inEnd || inBegin < 0 || inEnd > inorder.length) {
            return null;
        }
        if (postBegin >= postEnd || postBegin < 0 || postEnd > postorder.length) {
            return null;
        }

        TreeNode node = new TreeNode(postorder[postEnd - 1]);
        int inorderMidIndex = findIndex(inorder, node.val);
        System.out.println("left recursion, in: " + inBegin + "," + inorderMidIndex);
        System.out.println("left recursion, post: " + postBegin + "," + (postBegin + (inorderMidIndex - inBegin)));

        node.left = recursion(inorder, inBegin, inorderMidIndex,
                postorder, postBegin, postBegin + (inorderMidIndex - inBegin));
        node.right = recursion(inorder, inorderMidIndex + 1, inEnd,
                postorder, postBegin + (inorderMidIndex - inBegin) + 1, postEnd - 1);

        return node;
    }

    int findIndex(int[] array, int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /*
         * 中序遍历 inorder = [9,3,15,20,7]
         * 后序遍历 postorder = [9,15,7,20,3]
         * 返回如下的二叉树：
         *
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                3, 9 , 20, null, null, 15, 7));
        L106BuildTreeInOrderAndPostOrder test = new L106BuildTreeInOrderAndPostOrder();
        TreeNode node = test.buildTree(
                new int[] {9,3,15,20,7},
                new int[] {9,15,7,20,3});
        System.out.println(node);
    }
}
