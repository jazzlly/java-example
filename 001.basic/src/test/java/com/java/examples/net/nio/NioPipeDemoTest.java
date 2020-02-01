package com.java.examples.net.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Pipe;

import static org.junit.Assert.*;

public class NioPipeDemoTest {

    @Test
    public void readWriteInteger() throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        Pipe.SourceChannel sourceChannel = pipe.source();

        ByteBuffer buffer = ByteBuffer.allocate(64);
        IntBuffer intBuffer = buffer.asIntBuffer();
        intBuffer.put(256);

        buffer.limit(4);
        // buffer.flip();

        sinkChannel.write(buffer);

        ByteBuffer buffer1 = ByteBuffer.allocate(64);
        IntBuffer intBuffer1 = buffer1.asIntBuffer();
        sourceChannel.read(buffer1);

        intBuffer.limit(1);

        System.out.println(intBuffer1.get());
    }
}