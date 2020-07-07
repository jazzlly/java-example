package com.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，
 * 或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。
 * 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 */
public class L449SerilizeTree {

    StringBuilder postOrderRecursion(TreeNode node, StringBuilder builder) {
        if (node == null) {
            return builder;
        }

        postOrderRecursion(node.left, builder);
        postOrderRecursion(node.right, builder);

        builder.append(node.val);
        builder.append(";");

        return builder;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = postOrderRecursion(root, new StringBuilder());
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    TreeNode helper(int low, int high, Deque<Integer> deque) {
        if (deque.isEmpty()) {
            return null;
        }
        Integer val = deque.getLast();
        if (val < low || val > high) {
            return null;
        }

        deque.removeLast();
        TreeNode node = new TreeNode(val);
        node.right = helper(node.val, high, deque);
        node.left = helper(low, node.val, deque);

        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        Deque<Integer> deque = new LinkedList<>();

        for (String s : data.split(";")) {
            deque.addLast(Integer.parseInt(s));
        }
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, deque);

    }

    public static void main(String[] args) {
        TreeNode n11 = new TreeNode(20);
        TreeNode n21 = new TreeNode(5);
        TreeNode n22 = new TreeNode(40);

        n11.left = n21;
        n11.right = n22;

        TreeNode n31 = new TreeNode(2);
        TreeNode n32 = new TreeNode(15);
        TreeNode n33 = new TreeNode(30);
        TreeNode n34 = new TreeNode(50);

        n21.left = n31;
        n21.right = n32;
        n22.left = n33;
        n22.right = n34;

        TreeNode n41 = new TreeNode(4);
        TreeNode n42 = new TreeNode(13);

        n31.right = n41;
        n32.left = n42;

        // Your Codec object will be instantiated and called as such:
        L449SerilizeTree codec = new L449SerilizeTree();
        System.out.println(codec.serialize(n11));

        TreeNode root = codec.deserialize(codec.serialize(n11));
        System.out.println("ok");


        System.out.println(codec.serialize(null));
        System.out.println(codec.deserialize(codec.serialize(null)));

        String[] tmp = "".split(";");
        System.out.println(tmp);

    }
}
