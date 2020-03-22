package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class L102BinaryTreeTravel {
    /* 深度优先搜索的模板
    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        dfs(root.left);
        dfs(root.right);
    }
     */

    List<List<Integer>> retList = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return retList;
    }

    /**
     * 深度优先搜索
     * @param node
     * @param level
     */
    public void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (retList.size() <= level) {
            retList.add(new ArrayList<>());
        }
        List<Integer> list = retList.get(level);
        list.add(node.val);

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }


    /**
     * 广度优先搜索
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        assert root != null;

        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(currentLevel);
        }

        return list;
    }

}
