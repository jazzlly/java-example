package com.leetcode.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class L133CloneGraphic {
    
    Map<Node, Node> nodeMap = new HashMap<>();
    Set<Node> visited = new HashSet<>();
    
    public Node cloneGraph1(Node node) {
        if (node == null) {
            return null;
        }
        
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }
        
        Node clone = new Node(node.val);
        nodeMap.put(node, clone);
        
        for(Node child: node.neighbors) {
            Node cloneChild = cloneGraph(child);
            if (cloneChild != null) {
                clone.neighbors.add(cloneChild);
            }
        }
        
        return clone;
    }
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if (visited.contains(n)) {
                    continue;
                }

                Node clone = getNodeFromMap(n);
                
                for (Node child : n.neighbors) {
                    clone.neighbors.add(getNodeFromMap(child));
                    queue.add(child);
                }
                visited.add(n);
            }
        }
        
        return nodeMap.get(node);
    }

    Node getNodeFromMap(Node node) {
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }

        Node tmp = new Node(node.val);
        nodeMap.put(node, tmp);
        return tmp;
    }
    
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    
    public static void main(String[] args) {
        
    }
    
}
