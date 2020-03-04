package com.leetcode.stack;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L20ParenthesisMatchTest {

    @Test
    public void smoke() {
        String s = "(){}[][()]";
        assertThat(L20ParenthesisMatch.isValid(s)).isTrue();


        assertThat(L20ParenthesisMatch.isValid("")).isTrue();

        assertThat(L20ParenthesisMatch.isValid("]")).isFalse();

        assertThat(L20ParenthesisMatch.isValid("(")).isFalse();
        assertThat(L20ParenthesisMatch.isValid("()[")).isFalse();
        assertThat(L20ParenthesisMatch.isValid("{[)}")).isFalse();

    }


}