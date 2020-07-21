package com.leetcode.tree.middle;

import com.leetcode.tree.utils.TreeUtils;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class L116AddNextNode {
    public Node connect(Node root) {
        recursion(root);
        return root;
    }

    private void recursion(Node node) {
        if (node == null) {
            return;
        }

        Node left = node.left;
        Node right = node.right;
        while (left != null && right != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }

        recursion(node.left);
        recursion(node.right);
    }

    public static void main(String[] args) {

        Node root = TreeUtils.makeNodeBinaryTree(
                1, 2, 3, 4, 5, 6, 7);

        L116AddNextNode test = new L116AddNextNode();
        Node ans = test.connect(root);
        System.out.println("haha!");
    }

}


