package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

/**
 * 988. 从叶结点开始的最小字符串
 * 给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个从 0 到 25 的值，
 * 分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表 'b'，依此类推。
 *
 * 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 *
 * （小贴士：字符串中任何较短的前缀在字典序上都是较小的：
 * 例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。）
 */
public class L988SmallestLeaf {

    String minWord = "~";
    public String smallestFromLeaf(TreeNode root) {
        recursion(root, new StringBuilder());
        return minWord;
    }

    private void recursion(TreeNode node, StringBuilder current) {
        if (node == null) {
            return;
        }

        try {
            current.append((char) ('a' + node.val));

            // 叶子节点
            if (node.left == null && node.right == null) {
                current.reverse();
                String tmp = current.toString();
                current.reverse();

                if (minWord.compareTo(tmp) > 0) {
                    minWord = tmp;
                }
                return;
            }

            recursion(node.left, current);
            recursion(node.right, current);
        } finally {
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("hell" + (char)( 'a' + 1));

        TreeNode root = TreeUtils.makeBinaryTree(0,1,2,3,4,3,4);
        L988SmallestLeaf test = new L988SmallestLeaf();
        test.smallestFromLeaf(root);
        System.out.println(test.minWord);

        test.minWord = "~";
        test.smallestFromLeaf(TreeUtils.makeBinaryTree(25,1,3,1,3,0,2));
        System.out.println(test.minWord);

        test.minWord = "~";
        test.smallestFromLeaf(TreeUtils.makeBinaryTree(2,2,1,null,1,0,null,0));
        System.out.println(test.minWord);

        test.minWord = "~";
        test.smallestFromLeaf(TreeUtils.makeBinaryTree(0,null,1));
        System.out.println(test.minWord);
    }
}
