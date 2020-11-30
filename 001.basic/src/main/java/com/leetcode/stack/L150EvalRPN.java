package com.leetcode.stack;

import java.util.Stack;

public class L150EvalRPN {

//    public int evalRPN1(String[] tokens) {
//        Stack<String> stack = new Stack<>();
//
//        for (String s : tokens) {
//            if ("+".equals(s) || "-".equals(s)
//                || "*".equals(s) || "/".equals(s)) {
//                Integer b = Integer.parseInt(stack.pop());
//                Integer a = Integer.parseInt(stack.pop());
//                stack.push("" + calc(a, b, s));
//            } else {
//                stack.push(s);
//            }
//        }
//
//        return Integer.parseInt(stack.pop());
//    }

    private Integer calc(Integer a, Integer b, Character op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        throw new IllegalArgumentException("");
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String s : tokens) {
            if ("+".equals(s) || "-".equals(s)
                    || "*".equals(s) || "/".equals(s)) {
                Integer b = stack.pop();
                Integer a = stack.pop();
                stack.push(calc(a, b, s.charAt(0)));
            } else {
                stack.push(Integer.parseInt(s));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        L150EvalRPN test = new L150EvalRPN();
//        System.out.println(test.evalRPN(
//                new String[]{"2", "1", "+", "3", "*"}
//        ));
        System.out.println(test.evalRPN(
                new String[]{"4", "13", "5", "/", "+"}
        ));

        System.out.println(test.evalRPN(
                new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
        ));
    }
}
