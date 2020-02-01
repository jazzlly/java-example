package com.java.examples.net.nio.buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;

public class NioFileDemoTest {
    // http://tutorials.jenkov.com/java-nio/buffers.html

    @Test
    public void write() {
        try {
            RandomAccessFile file = new RandomAccessFile("/tmp/random1", "rw");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("1哇cas2哈哈哈\n".getBytes(Charset.forName("utf8")));
            buffer.put("2哇cas2哈哈哈\n".getBytes(Charset.forName("utf8")));
            buffer.put("3哇cas2哈哈哈\n".getBytes(Charset.forName("utf8")));
            buffer.flip();

            FileChannel channel = file.getChannel();
            channel.write(buffer);
            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * simpleRead的问题是如果buffer的尺寸和file中utf8编码内容不一致，
     * 比如会将一个3byte的utf8编码拆开，则会导致部分读问题
     */
    @Test
    public void simpleRead() {
        try {
            RandomAccessFile file = new RandomAccessFile("/tmp/random1", "rw");
            ByteBuffer buffer = ByteBuffer.allocate(1024); // todo: 如果这里将1024减小为2, 则会出问题

            FileChannel channel = file.getChannel();

            int count = 0;
            while ((count = channel.read(buffer)) != -1) {
                buffer.flip();

                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.println(new String(bytes, Charset.forName("utf8")));
                buffer.compact();
            }

            channel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readWithUtf8Decoder() {
        try {
            RandomAccessFile file = new RandomAccessFile("/tmp/random1", "rw");
            ByteBuffer buffer = ByteBuffer.allocate(1);
            FileChannel channel = file.getChannel();
            Queue<Byte> queue = new LinkedList<>();

            int count = 0;

            StringBuilder stringBuilder = new StringBuilder();
            while ((count = fillQueue(channel, buffer, queue)) != -1) {
                short first = (short) queue.peek();
                if (first < 0) {
                    first += 256;
                }

                if (first >> 4 == 0xe) {
                    byte[] bytes = new byte[3];
                    while (queue.size() < 3) {
                        count = fillQueue(channel, buffer, queue);
                        if (count == -1) {
                            throw new IllegalStateException("file content correct!");
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        bytes[i] = (byte) (queue.remove() & 0xff);
                    }
                    stringBuilder.append(new String(bytes, "utf8"));
                    // System.out.println(new String(bytes, "utf8"));
                }

                if (first >> 7 == 0) {
                    char c = (char) queue.remove().byteValue();
                    // 实现一个readline的逻辑
                    if (c == '\n') {
                        System.out.println(stringBuilder.toString());
                        stringBuilder = new StringBuilder();
                    } else {
                        stringBuilder.append(c);
                    }
                }
            }
            channel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int fillQueue(FileChannel channel, ByteBuffer buffer, Queue<Byte> queue) throws IOException {
        int count = channel.read(buffer);
        if (count == -1) {
            return count;
        }

        buffer.flip();
        for (int i = 0; i < count; i++) {
            queue.offer(buffer.get());
        }
        buffer.compact();
        return count;
    }

    @Test
    public void copyFile() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("/tmp/random1", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("/tmp/random2", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    @Test
    public void foo() {
        System.out.println(0xff >>> 7);
        System.out.println(0x7f >>> 7);

        byte b = (byte) 0xe5;
        short s = (short) b;
        System.out.println(s);
        System.out.println(256 + b);
        System.out.println(~(-28));
        System.out.println((short)0xe5);
//        System.out.println((byte)0xe5);
//        System.out.println((short)0xe5 >> 4);
//        System.out.println((byte) -27);

        System.out.println( ((byte) -27) & 0x60);

    }

    @Test
    public void QueueTest() {
        Queue<Byte> queue = new LinkedList<>();
        queue.offer((byte) 'a');
        queue.offer((byte) 'b');
        System.out.println(queue.peek());
        System.out.println("queue size: " + queue.size());
        System.out.println(queue.remove());
        System.out.println("queue size: " + queue.size());
        System.out.println(queue.remove());
    }

}