package com.pekall;

import org.junit.Test;

public class BytesTest {

    @Test
    public void testInitValue() {
        byte[] bytes = new byte[32];
        System.out.println(GsonUtils.toJson(bytes));
    }
}
