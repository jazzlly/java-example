package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 655. 输出二叉树
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 *
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 * 示例 1:
 *
 * 输入:
 *      1
 *     /
 *    2
 * 输出:
 * [["", "1", ""],
 *  ["2", "", ""]]
 * 示例 2:
 *
 * 输入:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 * 示例 3:
 *
 * 输入:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * 注意: 二叉树的高度在范围 [1, 10] 中。
 */
public class L655PrintBinaryTreeBfs {

    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);

        // 满二叉树的性质
        String[][] array = new String[height][(1 << height) - 1];
        for (int i = 0; i < height; i++) {
            Arrays.fill(array[i], "");
        }

        fill(array, root);
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ans.add(Arrays.asList(array[i]));
        }
        return ans;
    }

    private void fill(String[][] array, TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(root, 0, 0, array[0].length));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NodeInfo node = queue.poll();
                array[node.level][(node.l+node.r)/2] = "" + node.node.val;
                if (node.node.left != null) {
                    queue.add(new NodeInfo(node.node.left, node.level + 1, node.l, (node.l + node.r)/2));
                }
                if (node.node.right != null) {
                    queue.add(new NodeInfo(node.node.right, node.level + 1, (node.l + node.r)/2 + 1, node.r));
                }
            }
        }
    }

    int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left) ,getHeight(node.right));
    }

    class NodeInfo {
        TreeNode node;
        int level;
        int l;
        int r;

        public NodeInfo(TreeNode node, int level, int l, int r) {
            this.node = node;
            this.level = level;
            this.l = l;
            this.r = r;
        }
    }
}
