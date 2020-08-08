package com.leetcode.tree.middle;

/**
 * 255. 验证前序遍历序列二叉搜索树
 * 给定一个整数数组，你需要验证它是否是一个二叉搜索树正确的先序遍历序列。
 *
 * 你可以假定该序列中的数都是不相同的。
 */
public class L255VerifyBst {
    /*
     [5,2,6,1,3]
     0 left 2n + 1, right: 2n + 2
     1 left 3 4

     index: 偶数，右子树  parent = (2n+2)/2-1
            奇数：左子树  parent = ((2n+1)-1)/2
     */
    public boolean verifyPreorder(int[] preorder) {
        // todo:
        return false;
    }
}
