package com.leetcode.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L17LetterCombination {

    private List<String> ans = new ArrayList<>();
    private static Map<Character, Character[]> digitMap = new HashMap<>();
    static {
        digitMap.put('2', new Character[]{'a', 'b', 'c'});
        digitMap.put('3', new Character[]{'d', 'e', 'f'});
        digitMap.put('4', new Character[]{'g', 'h', 'i'});
        digitMap.put('5', new Character[]{'j', 'k', 'l'});
        digitMap.put('6', new Character[]{'m', 'n', 'o'});
        digitMap.put('7', new Character[]{'p', 'q', 'r', 's'});
        digitMap.put('8', new Character[]{'t', 'u', 'v'});
        digitMap.put('9', new Character[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return ans;
        }

        dfs("", digits, 0);
        return ans;
    }

    void dfs(String current, String digits, int index) {
        if (current.length() == digits.length()) {
            ans.add(current);
            return;
        }

        char d = digits.charAt(index);
        for (Character letter : digitMap.get(d)) {
            dfs(current + letter.toString(), digits, index + 1);
        }
    }

    L17LetterCombination reset() {
        ans = new ArrayList<>();
        return this;
    }
}
