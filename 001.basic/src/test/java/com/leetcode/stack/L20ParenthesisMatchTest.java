package com.leetcode.stack;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L20ParenthesisMatchTest {

    @Test
    public void smoke() {
        L20ParenthesisMatch test = new L20ParenthesisMatch();
        assertThat(test.isValid("()")).isTrue();


        /*
        assertThat(test.isValid("")).isTrue();

        assertThat(test.isValid("]")).isFalse();

        assertThat(test.isValid("(")).isFalse();
        assertThat(test.isValid("()[")).isFalse();
        assertThat(test.isValid("{[)}")).isFalse();
        */
    }


}