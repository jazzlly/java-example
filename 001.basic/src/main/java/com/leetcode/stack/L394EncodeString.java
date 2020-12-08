package com.leetcode.stack;


import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;   

public class L394EncodeString {
    
    int pos = 0;
    
    public String decodeString(String s) {
        pos = 0;
        
        LinkedList<String> stack = new LinkedList<>();
        while (pos < s.length()) {
            char current = s.charAt(pos);
            
            if (Character.isDigit(current)) {
                String digit = getDigit(s);
                stack.addLast(digit);
                continue;
            }
            
            if (Character.isLetter(current) || '[' == current) {
                stack.add(String.valueOf(current));
                pos++;
                continue;
            }
            
            if (']' == current) {
                pos++;

                LinkedList<String> tmp = new LinkedList<>();
                while (!"[".equals(stack.peekLast())) {
                    tmp.addFirst(stack.removeLast());
                }
                stack.removeLast(); // remove [

                StringBuilder sb = new StringBuilder();
                for (String string : tmp) {
                    sb.append(string);
                }
                String tmpStr = sb.toString();
                
                Integer repeat = Integer.parseInt(stack.removeLast());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < repeat; i++) {
                    builder.append(tmpStr);
                }
                stack.addLast(builder.toString());
                continue;
            }

            throw new IllegalStateException("invalid char: " + current);
        }
        
        return getString(stack);
    }
    
    String getDigit(String s) {
        StringBuilder builder = new StringBuilder();
        while(pos < s.length() && Character.isDigit(s.charAt(pos))) {
            builder.append(s.charAt(pos));
            pos++;
        }
        return builder.toString();
    }
    
    String getString(LinkedList<String> stack) {
        StringBuilder builder = new StringBuilder();
        for (String string : stack) {
            builder.append(string);
        }
        
        return builder.toString();
    }

    public static void main(String[] args) {
        L394EncodeString test = new L394EncodeString();

        System.out.println(test.decodeString("3[a]2[bc]")); // aaabcbc
        assertThat(test.decodeString("3[a]2[bc]")).isEqualTo("aaabcbc");
        assertThat(test.decodeString("3[a2[c]]")).isEqualTo("accaccacc");
        assertThat(test.decodeString("2[abc]3[cd]ef")).isEqualTo("abcabccdcdcdef");
        assertThat(test.decodeString("abc3[cd]xyz")).isEqualTo("abccdcdcdxyz");
        assertThat(test.decodeString("")).isEqualTo("");
        assertThat(test.decodeString("a")).isEqualTo("a");
        assertThat(test.decodeString("[]")).isEqualTo("[]");
    }
    
}
