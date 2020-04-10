package com.leetcode.tree;

import java.util.List;

public class Node {
    public int val;
    public List<Node> children;
    public Node parent;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public Node(int _val, List<Node> _children, Node parent) {
        val = _val;
        children = _children;
        this.parent = parent;
    }
};