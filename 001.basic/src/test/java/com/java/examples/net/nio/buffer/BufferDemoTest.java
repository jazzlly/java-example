package com.java.examples.net.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class BufferDemoTest {

    @Test
    public void smoke() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("是".getBytes(Charset.defaultCharset()));
        buffer.flip();

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.defaultCharset()));
    }

    @Test
    public void compact() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("一二三四".getBytes(Charset.forName("UTF-8")));
        buffer.flip();

        // 读取三个字符
        byte[] bytes = new byte[9];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.forName("UTF-8")));

        buffer.compact(); // 将 '四' 移动到缓冲区的最前面

        buffer.put("娃哈哈".getBytes());
        buffer.flip();

        bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.defaultCharset()));
    }

    @Test
    public void compact2() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("一二三四".getBytes(Charset.forName("UTF-8")));
        buffer.flip();

        // 读取四个字符
        byte[] bytes = new byte[12];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.forName("UTF-8")));

        buffer.compact(); // 没有字符被移动

        buffer.put("娃哈哈".getBytes());
        buffer.flip();

        bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.defaultCharset()));
    }

    @Test
    public void compact3() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("一二三四".getBytes(Charset.forName("UTF-8")));
        buffer.flip();

        // 读取四个字符
        byte[] bytes = new byte[15];
        buffer.get(bytes); // got BufferUnderflowException
        System.out.println(new String(bytes, Charset.forName("UTF-8")));

        buffer.compact(); // 没有字符被移动

        buffer.put("娃哈哈".getBytes());
        buffer.flip();

        bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.defaultCharset()));
    }


}