package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class L655PrintBinaryTreeRecursion {

    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);

        // 满二叉树的性质
        // String[][] array = new String[height][(2 << height) - 1];
        String[][] array = new String[height][(1 << height) - 1];
        for (int i = 0; i < height; i++) {
            Arrays.fill(array[i], "");
        }

        fill(array, root, 0, 0, array[0].length);
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ans.add(Arrays.asList(array[i]));
        }
        return ans;
    }

    private void fill(String[][] array, TreeNode node, int level, int l, int r) {
        if (node == null) {
            return;
        }

        array[level][(l+r)/2] = "" + node.val;
        fill(array, node.left, level + 1, l, (l+r)/2);
        fill(array, node.right, level + 1, (l+r)/2 + 1, r);
    }

    int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left) ,getHeight(node.right));
    }
}
