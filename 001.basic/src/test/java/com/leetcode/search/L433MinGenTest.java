package com.leetcode.search;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L433MinGenTest {

    @Test
    public void smoke1() {
        assertThat(new L433MinGen().minMutation(
                "AACCGGTT",
                "AACCGGTA",
                new String[] {"AACCGGTA"} )).isEqualTo(1);
    }

    @Test
    public void smoke2() {
        assertThat(new L433MinGen().minMutation(
                "AACCGGTT",
                "AAACGGTA",
                new String[] {"AACCGGTA", "AACCGCTA", "AAACGGTA"} )).isEqualTo(2);
    }

    @Test
    public void smoke3() {
        assertThat(new L433MinGen().minMutation(
                "AAAAACCC",
                "AACCCCCC",
                new String[] {"AAAACCCC", "AAACCCCC", "AACCCCCC"} )).isEqualTo(3);
    }
    /*
    "AAAAAAAA"
"CCCCCCCC"
["AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"]
     */
    @Test
    public void smoke4() {
        assertThat(new L433MinGen().minMutation(
                "AAAAAAAA",
                "CCCCCCCC",
                new String[] {"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC",
                        "AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"} ))
                .isEqualTo(-1);
    }
}