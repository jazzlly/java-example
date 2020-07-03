package com.leetcode.tree;

import java.util.*;

/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 *
 *
 * 案例 2:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * 输出: False
 *
 */
public class L653TwoSumBst {

    /*
    int target = 0;
    boolean found = false;
    Set<Integer> set = new HashSet<>();

    public boolean findTarget1(TreeNode root, int k) {
        target = k;
        recursion1(root);
        return found;
    }

    private void recursion1(TreeNode node) {
        if (node == null || found) {
            return;
        }

        if (set.contains(target - node.val)) {
            found = true;
            return;
        }
        set.add(target - node.val);

        recursion1(node.left);
        recursion1(node.right);
    }

    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> deque = new LinkedList<>();
        recursion(root, deque);

        while (!deque.isEmpty()) {
            TreeNode first = deque.peekFirst();
            TreeNode last = deque.peekLast();
            if (first == null || last == null || first == last) {
                break;
            }

            int sum = first.val + last.val;
            if (k == sum) {
                return true;
            } else if (sum > k) {
                deque.pollLast();
            } else {
                deque.pollFirst();
            }
        }

        return false;
    }

    private void recursion(TreeNode node, Deque<TreeNode> deque) {
        if (node == null) {
            return;
        }

        recursion(node.left, deque);
        deque.add(node);
        recursion(node.right, deque);
    }*/






















    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        middleOrderRecursion(root, list);

        int first = 0;
        int last = list.size() - 1;
        while (first < last && last < list.size()) {
            int head = list.get(first);
            int tail = list.get(last);
            if (head + tail == k) {
                return true;
            }
            if (head + tail < k) {
                first++;
            } else {
                last--;
            }
        }
        return false;
    }

    private void middleOrderRecursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        middleOrderRecursion(node.left, list);
        list.add(node.val);
        middleOrderRecursion(node.right, list);
    }
}
