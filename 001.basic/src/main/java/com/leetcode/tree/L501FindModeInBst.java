package com.leetcode.tree;

import java.util.*;

public class L501FindModeInBst {

    TreeNode preNode = null;
    int maxCnt = 0;
    int currentCnt = 0;
    List<Integer> result = new LinkedList<>();

    public int[] findMode(TreeNode root) {
        recursion(root);

        int[] res = new int[result.size()];
        int cnt = 0;
        for (Integer r : result) {
            res[cnt++] = r;
        }
        return res;
    }

    private void recursion(TreeNode node) {
        if (node == null) {
            return;
        }

        recursion(node.left);
        if (preNode != null && preNode.val == node.val) {
            currentCnt++;
        } else {
            currentCnt = 1;
        }

        if (currentCnt > maxCnt) {
            maxCnt = currentCnt;
            result.clear();
            result.add(node.val);
        } else if (currentCnt == maxCnt) {
            result.add(node.val);
        }

        preNode = node;
        recursion(node.right);
    }
}
