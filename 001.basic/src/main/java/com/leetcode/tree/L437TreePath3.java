package com.leetcode.tree;

import java.util.*;

/**
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *  currSum 0       map 0   1
 *          10           10  1
 *          15
 *
 *       10
 *      /  \
 *     5*   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class L437TreePath3 {
    int sum = 0;
    int ans = 0;

    public int pathSum1(TreeNode root, int sum) {
        this.sum = sum;

        recursion1(root, new ArrayList<>());
        return ans;
    }

    private void recursion1(TreeNode node, List<Integer> treePath) {
        if (node == null) {
            return;
        }

        int currentSum = node.val;
        if (currentSum == sum) {
            ans++;
        }
        for (int i = treePath.size() - 1; i >= 0 ; i--) {
            currentSum += treePath.get(i);
            if (currentSum == sum) {
                ans++;
            }
        }

        if (node.left != null || node.right != null) {
            List<Integer> tmp = new ArrayList<>(treePath);
            tmp.add(node.val);
            recursion1(node.left, tmp);
            recursion1(node.right, tmp);
        }
    }

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> pathSumMap = new HashMap<>();
        pathSumMap.put(0, 1);

        return recursion(root, pathSumMap, sum, 0);
    }

    private int recursion(TreeNode node, Map<Integer, Integer> pathSumMap, int target, int currSum) {
        if (node == null) {
            return 0;
        }

        int ans = 0;
        currSum += node.val;
        if (pathSumMap.getOrDefault(currSum - target, 0) > 0) {
            ans++;
        }

        pathSumMap.put(currSum, pathSumMap.getOrDefault(currSum, 0) + 1);

        ans += recursion(node.left, pathSumMap, target, currSum);
        ans += recursion(node.right, pathSumMap, target, currSum);

        pathSumMap.put(currSum, pathSumMap.get(currSum) - 1);

        return ans;
    }
}
