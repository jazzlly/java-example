package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.*;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 *
 *
 *
 * 示例 1：
 * 输入:
 *
 *   5
 *  /  \
 * 2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 *
 * 示例 2：
 * 输入：
 *
 *   5
 *  /  \
 * 2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 *
 *
 *
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 */
public class L508SubtreeSumFreq {
    int maxSum = Integer.MIN_VALUE;
    Map<Integer, Integer> sumMap = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[] {};
        }

        treeSum(root);

        List<Integer> tmp = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumMap.entrySet()) {
            if (entry.getValue().equals(maxSum)) {
                tmp.add(entry.getKey());
            }
        }

        int[] ans = new int[tmp.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = tmp.get(i);
        }
        return ans;
    }

    int treeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int sum = treeSum(node.left) + treeSum(node.right) + node.val;
        int cnt = sumMap.getOrDefault(sum, 0) + 1;
        maxSum = Math.max(maxSum, cnt);
        sumMap.put(sum, cnt);

        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                5, 2, -3));
        L508SubtreeSumFreq test = new L508SubtreeSumFreq();

        System.out.println(test.findFrequentTreeSum(root));
    }
}
