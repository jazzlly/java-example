package com.leetcode.search;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class L127WordLadderTest {

    /*
    输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-ladder
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void smoke() {
        L127WordLadder test = new L127WordLadder();
        assertThat(test.ladderLength("hit", "cog",
                Arrays.asList("hot","dot","dog","lot","log","cog")))
            .isEqualTo(5);
        ;

    }

    @Test
    public void setTest() {
        Set<String> s1 = new HashSet<>(Arrays.asList("1"));
        Set<String> s2 = new HashSet<>(Arrays.asList("2"));
        System.out.println(s1.retainAll(s2));
        System.out.println(s1);
    }
}