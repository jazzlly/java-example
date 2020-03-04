package com.leetcode.hash;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L242AnagramTest {

    @Test
    public void smoke() {
        assertThat(L242Anagram.isAnagramHash(null, null)).isTrue();
        assertThat(L242Anagram.isAnagramHash("", "")).isTrue();
        assertThat(L242Anagram.isAnagramHash("foo", "foo")).isTrue();
        assertThat(L242Anagram.isAnagramHash("cat", "act")).isTrue();

        assertThat(L242Anagram.isAnagramHash("foo", "bar")).isFalse();
    }

    @Test
    public void smoke2() {
        assertThat(L242Anagram.isAnagramHash("cat", "act")).isTrue();
    }
}