package com.leetcode.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L77Combination {

    private List<List<Integer>> result = new ArrayList<>();
    private int max;
    private int number;
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) {
            return result;
        }

        max = n;
        number = k;

        Set<Integer> currentSet = new HashSet<>();
        for (int i = 1; i <= max; i++) {
            currentSet.add(i);
        }
        dfs(currentSet, new HashSet<>());

        return result;
    }

    void dfs(Set<Integer> currentSet, Set<Integer> currentResult) {

        if (currentResult.size() == number) {
            result.add(new ArrayList<>(currentResult));
            return;
        }

        Set<Integer> traveled = new HashSet<>();
        for (Integer integer : currentSet) {
            // 剪枝： 如果候选数字小于还需要选择的数字， 则退出
            // 剩下多少候选的数字
            int numberLeft = currentSet.size() - traveled.size();
            // 还需要选择多少个数字
            int toSelect = number - currentResult.size();
            if (numberLeft < toSelect) {
                break;
            }

            currentResult.add(integer);
            traveled.add(integer);

            Set<Integer> tmp = new HashSet<>(currentSet);
            tmp.removeAll(traveled);

            dfs(tmp, currentResult);
            currentResult.remove(integer);
        }

    }
}
