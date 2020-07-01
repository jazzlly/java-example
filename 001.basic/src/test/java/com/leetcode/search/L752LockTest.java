package com.leetcode.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class L752LockTest {

    @Test
    public void smoke() {
        L752Lock test = new L752Lock();

        System.out.println(test.openLock(
                new String[]{"8888"}, "0009"));

        System.out.println(test.openLock(
                new String[]{"0201","0101","0102","1212","2002"}, "0202"));

        System.out.println(test.openLock(
                new String[]{"0000"}, "8888"));

        System.out.println(test.openLock(
                new String[]{"0001","0009", "0010", "0090", "0100", "0900", "1000", "9000" }, "2222"));

        System.out.println(test.openLock(
                new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));

    }
}