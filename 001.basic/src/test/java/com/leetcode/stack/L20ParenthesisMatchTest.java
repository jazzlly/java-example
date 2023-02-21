package com.leetcode.stack;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L20ParenthesisMatchTest {

    @Test
    public void smoke() {
        L20ParenthesisMatch2 test = new L20ParenthesisMatch2();
        assertThat(test.isValid("()")).isTrue();

        assertThat(test.isValid("")).isTrue();

        assertThat(test.isValid("]")).isFalse();

        assertThat(test.isValid("(")).isFalse();
        assertThat(test.isValid("()[")).isFalse();
        assertThat(test.isValid("{[)}")).isFalse();
    }
}