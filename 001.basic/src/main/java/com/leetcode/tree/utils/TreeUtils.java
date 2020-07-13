package com.leetcode.tree.utils;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
    public static TreeNode makeBinaryTree(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        List<TreeNode> treeNodes = new ArrayList<>();
        for (Integer integer : list) {
            if (integer != null) {
                treeNodes.add(new TreeNode(integer));
            } else {
                treeNodes.add(null);
            }
        }

        for (int i = 0; i < treeNodes.size(); i++) {
            TreeNode current = treeNodes.get(i);
            if (current == null) {
                continue;
            }

            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            if (leftIndex < treeNodes.size()) {
                current.left = treeNodes.get(leftIndex);
            }
            if (rightIndex < treeNodes.size()) {
                current.right = treeNodes.get(rightIndex);
            }
        }
        return treeNodes.get(0);
    }
}
