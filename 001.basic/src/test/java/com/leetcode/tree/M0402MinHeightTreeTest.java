package com.leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class M0402MinHeightTreeTest {
    @Test
    public void smoke() {
        M0402MinHeightTree test = new M0402MinHeightTree();
        TreeNode node = test.sortedArrayToBST(new int[] {0, 1, 2, 3, 4});

        System.out.println(node);
    }
}