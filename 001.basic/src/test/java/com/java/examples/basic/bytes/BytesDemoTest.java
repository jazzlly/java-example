package com.java.examples.basic.bytes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BytesDemoTest {

    @Test
    public void foo() {
        byte a = (byte) 0x80;
        System.out.println(a);

        System.out.println(256 + a);

        byte b = (byte) 0xc0;
        System.out.println(b);
        System.out.println(256 + b);
    }


    @Test
    public void toUnsignedShort() {
        byte b = (byte) 0xc0;
        System.out.println(b);
        System.out.println(256 + b);

        assertThat(256 + b).isEqualTo(0xc0);
    }
}