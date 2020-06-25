package com.leetcode.tree;

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 *
 * 输入:
 *     2
 *    / \
 *   2   2
 *
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 */
public class L671SecondMin {

//    Set<Integer> set = new TreeSet<>();
//
//    public int findSecondMinimumValue(TreeNode root) {
//        recursion(root);
//
//        if (set.size() < 2) {
//            return -1;
//        }
//
//        for (Integer integer : set) {
//            System.out.println(integer);
//        }
//
//        set.remove(set.iterator().next());
//        return set.iterator().next();
//    }
//
//    private void recursion(TreeNode node) {
//        if (node == null) {
//            return;
//        }
//
//        set.add(node.val);
//
//        recursion(node.left);
//        recursion(node.right);
//    }

    int result = -1;
    public int findSecondMinimumValue(TreeNode root) {
        recursion(root);
        return result;
    }

    private void recursion(TreeNode node) {
        if (result != -1 || node == null) {
            return;
        }

        if (node.left != null && node.left.val > node.val) {
            result = node.left.val;
            return;
        }
        if (node.right != null && node.right.val > node.val) {
            result = node.right.val;
            return;
        }

        recursion(node.left);
        recursion(node.right);
    }
}
