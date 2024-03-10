package com.leetcode.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L17LetterCombination2 {

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

        search("", 0, digits);
        return ans;
    }

    private void search(String result, int level, String digits) {
        if (result.length() == digits.length()) {
            ans.add(result);
            return;
        }

        Character c = new Character(digits.charAt(level));
        for (Character letter : digitMap.get(c)) {
            search(result + letter, level + 1, digits);
        }
    }
}
