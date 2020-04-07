package com.leetcode.search;

import org.junit.Test;

import java.util.Arrays;

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
}