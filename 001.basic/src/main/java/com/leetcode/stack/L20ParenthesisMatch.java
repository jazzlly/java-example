package com.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L20ParenthesisMatch {
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                stack.push(c);
                continue;
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
                continue;
            }
        }

        return stack.isEmpty();
    }
}
