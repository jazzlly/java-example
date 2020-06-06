package com.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L20ParenthesisMatch {
    public static boolean isValid2(String s) {
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

    public boolean isValid(String s) {
        // 右边括号入栈时，栈顶必须是匹配的左括号，并且左括号出栈
        // 左括号直接入栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (isLeft(c)) {
                stack.push(c);
                continue;
            }

            char top = stack.isEmpty() ? 'x' : stack.peek();
            if (!bracketMatch(top, c)) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    boolean isLeft(char c) {
        return (c == '(' || c == '[' || c == '{');
    }

    boolean bracketMatch(char left, char right) {
        if (left == '(') {
            return right == ')';
        }
        if (left == '[') {
            return right == ']';
        }
        if (left == '{') {
            return right == '}';
        }
        return false;
    }
}
