package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.*;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 *
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 *
 * 输入:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 */
public class L622BoardTravel {

    public int widthOfBinaryTree1(TreeNode root) {
        int ans = 0;
        Queue<PosTreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(new PosTreeNode(root, 0, 0));
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int headPos = queue.peek().pos;
            int tailPos = headPos;

            for (int i = 0; i < size; i++) {
                PosTreeNode node = queue.poll();
                tailPos = node.pos;

                if (node.node.left != null) {
                    queue.add(new PosTreeNode(
                            node.node.left,
                            node.depth + 1,
                            node.pos * 2));
                }
                if (node.node.right != null) {
                    queue.add(new PosTreeNode(
                            node.node.right,
                            node.depth + 1,
                            node.pos * 2 + 1));
                }
            }
            ans = Math.max(ans, tailPos - headPos + 1);
        }
        return ans;
    }

    class PosTreeNode {
        TreeNode node;
        int depth;
        int pos;

        public PosTreeNode(TreeNode node, int depth, int pos) {
            this.node = node;
            this.depth = depth;
            this.pos = pos;
        }
    }

    int ans;
    // 深度， 左边节点位置映射
    Map<Integer, Integer> leftPos;

    public int widthOfBinaryTree(TreeNode root) {
        ans = 0;
        leftPos = new HashMap<>();

        dfs(root, 0,0);
        return ans;
    }

    private void dfs(TreeNode node, int depth, int pos) {
        if (node == null) {
            return;
        }

        leftPos.putIfAbsent(depth, pos);
        ans = Math.max(ans, pos - leftPos.get(depth) + 1);

        dfs(node.left, depth + 1, pos * 2);
        dfs(node.right, depth + 1, pos * 2 + 1);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 3, 2, 5, 3, null, 9));
        L622BoardTravel test = new L622BoardTravel();
        System.out.println(test.widthOfBinaryTree(root));

        /**
         * *
         *  *           1
         *  *          /
         *  *         3
         *  *        / \
         *  *       5   3
         */
        root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 3, null, 5, 3));
        System.out.println(test.widthOfBinaryTree(root));

        root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 3, 2, 5));
        System.out.println(test.widthOfBinaryTree(root));

        root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 3, 2, 5, null, null, 9, 6, null, null, null, null, null, null, 7));
        System.out.println(test.widthOfBinaryTree(root));
    }
}
