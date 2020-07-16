package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.*;

/**
 * 652. 寻找重复的子树
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 * 示例 1：
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 下面是两个重复的子树：
 *
 *       2
 *      /
 *     4
 * 和
 *
 *     4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 */
public class L652FindSubTree {

    List<TreeNode> ans = new ArrayList<>();
    Map<String, Integer> trees = new HashMap<>();
    Map<Integer, Integer> counts = new HashMap<>();
    int t = 1;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        String id = new StringBuilder()
                .append(node.val)
                .append(",")
                .append(dfs(node.left))
                .append(",")
                .append(dfs(node.right))
                .toString();
        int uid = trees.computeIfAbsent(id, s -> t++);
        counts.put(uid, counts.getOrDefault(uid, 0 ) + 1);
        if (counts.get(uid) == 2) {
            ans.add(node);
        }
        return uid;
    }

    public static void main(String[] args) {
        /*
         *         1
         *        / \
         *       2   3
         *      /   / \
         *     4   2   4
         *        /
         *       4
         */
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 2, 3, 4, null, 2, 4, null, null, null, null, 4));
        L652FindSubTree test = new L652FindSubTree();
        List<TreeNode> nodes = test.findDuplicateSubtrees(root);
        System.out.println(nodes.toArray());

    }
}
