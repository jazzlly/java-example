package com.leetcode.tree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L236CommAncestorTest {

    @Test
    public void name() {
        // [3,5,1,6,2,0,8,null,null,7,4]
        /*
                             3
                      5             1
                   6     2       0     8
              nu    nu  7  4
          :
          5 4
         */
        TreeNode l00 = new TreeNode(3);
        TreeNode l10 = new TreeNode(5);
        TreeNode l11 = new TreeNode(1);

        TreeNode l20 = new TreeNode(6);
        TreeNode l21 = new TreeNode(2);
        TreeNode l22 = new TreeNode(0);
        TreeNode l23 = new TreeNode(8);

        TreeNode l30 = new TreeNode(7);
        TreeNode l31 = new TreeNode(4);

        l00.left = l10;
        l00.right = l11;

        l10.left = l20;
        l10.right = l21;
        l11.left = l22;
        l11.right = l23;
        l21.left = l30;
        l21.right = l31;

        TreeNode node = new L236CommAncestor().lowestCommonAncestor(l00, l10, l31);
        assertThat(node.val).isEqualTo(5);

    }
}