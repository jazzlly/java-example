package com.leetcode.stack;

import java.util.*;

public class L20ParenthesisMatch2 {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 右括号
                if (stack.empty()) {
                    return false;
                }

                if (!stack.pop().equals(map.getOrDefault(c, 'x'))) {
                    return false;
                }
            } else {
                // 左括号
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
