package com.leetcode.tree;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class L103ZigzagOrderTravel {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(reverse) {
                    level.addFirst(node.val);
                } else {
                    level.addLast(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            reverse = !reverse;
            result.add((List<Integer>) level);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode n11 = new TreeNode(1);
        TreeNode n21 = new TreeNode(2);
        TreeNode n22 = new TreeNode(3);

        n11.left = n21;
        n11.right = n22;

        TreeNode n31 = new TreeNode(4);
        TreeNode n32 = new TreeNode(5);
        n21.left = n31;
        n22.right = n32;

        L103ZigzagOrderTravel test = new L103ZigzagOrderTravel();
        List<List<Integer>> ans = test.zigzagLevelOrder(n11);
        System.out.println(ans.toString());
    }
}
