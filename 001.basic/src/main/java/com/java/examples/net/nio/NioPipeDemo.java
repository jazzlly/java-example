package com.java.examples.net.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Pipe;

public class NioPipeDemo {
    static Pipe pipe = null;
    static Pipe.SinkChannel sinkChannel = null;
    static Pipe.SourceChannel sourceChannel = null;

    public static void main(String[] args) {
        try {
            pipe = Pipe.open();
            sinkChannel = pipe.sink();
            sourceChannel = pipe.source();

            ByteBuffer buffer = ByteBuffer.allocate(64);
            IntBuffer intBuffer = buffer.asIntBuffer();
            intBuffer.put(0);

            buffer.limit(4);

            Thread writeThread = new Thread(() -> {
                while (true) {
                    try {
                        buffer.rewind();
                        sinkChannel.write(buffer);

                        intBuffer.flip();
                        int tmp = intBuffer.get() + 1;
                        System.out.println("Write thread write: " + tmp);
                        intBuffer.clear();
                        intBuffer.put(tmp);

                        Thread.sleep(2000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            ByteBuffer bufferRead = ByteBuffer.allocate(64);
            IntBuffer intBufferRead = bufferRead.asIntBuffer();
            intBufferRead.limit(1);

            Thread readThread = new Thread(() -> {
                while (true) {
                    try {
                        sourceChannel.read(bufferRead);
                        bufferRead.rewind();

                        System.out.println("Read Thread: " + intBufferRead.get());
                        intBufferRead.rewind();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            writeThread.start();
            readThread.start();

            Thread.sleep(50_000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
