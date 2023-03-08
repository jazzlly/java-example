package com.leetcode.tree;

import org.junit.Test;

import java.util.Arrays;

public class L429NthTreeLevelTravTest {

    @Test
    public void name() {
        Node l00 = new Node(1);
        Node l201 = new Node(2);
        Node l202 = new Node(3);
        Node l301 = new Node(4);
        Node l302 = new Node(5);
        Node l303 = new Node(6);
        Node l304 = new Node(7);

        l00.children = Arrays.asList(l201, l202);
        l201.children = Arrays.asList(l301, l302);
        l202.children = Arrays.asList(l303, l304);

        System.out.println(new L429NthTreeLevelTrav().levelOrder(l00));
        System.out.println(new L429NthTreeLevelTrav2().levelOrder(l00));

    }
}