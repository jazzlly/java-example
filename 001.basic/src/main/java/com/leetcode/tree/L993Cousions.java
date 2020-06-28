package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class L993Cousions {

    List<Integer> xPath = null;
    List<Integer> yPath = null;
    public boolean isCousins(TreeNode root, int x, int y) {
        recursion(root, x, y, new ArrayList<>());

        if (xPath == null || yPath == null ||
                xPath.size() < 3 || yPath.size() < 3) {
            return false;
        }
        return (xPath.size() == yPath.size()) &&
                (xPath.get(xPath.size() - 2) != yPath.get(yPath.size() - 2));
    }

    private void recursion(TreeNode node, int x, int y, List path) {
        if (node == null || (xPath != null && yPath != null)) {
            return;
        }

        path.add(node);
        if (node.val == x) {
            xPath = path;
        }
        if (node.val == y) {
            yPath = path;
        }

        recursion(node.left, x, y, new ArrayList<>(path));
        recursion(node.right, x, y, new ArrayList<>(path));
    }
}
