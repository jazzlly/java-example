package com.leetcode.tree;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class L700SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        // 1. 找到节点
        TreeNode current = root;
        while (current != null) {
            if (current.val == val) {
                break;
            }
            if (val < current.val) {
                current = current.left;
                continue;
            }
            if (val > current.val) {
                current = current.right;
            }
        }

        // 2. 生成子树
        return recursion(current);
    }

    private TreeNode recursion(TreeNode current) {
        if (current == null) {
            return null;
        }

        TreeNode node = new TreeNode(current.val);
        node.left = recursion(current.left);
        node.right = recursion(current.right);
        return node;
    }
}
