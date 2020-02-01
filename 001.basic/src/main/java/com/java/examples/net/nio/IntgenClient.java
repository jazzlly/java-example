package com.java.examples.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class IntgenClient {

    public static void main(String[] args) {
        int round = 100;
        SocketChannel client = null;
        try {
            SocketAddress address = new InetSocketAddress(8888);
            client = SocketChannel.open(address);

            ByteBuffer buffer = ByteBuffer.allocate(4);
            IntBuffer intBuffer = buffer.asIntBuffer();

            while (client.read(buffer) != -1) {
                // buffer.flip();
                int tmp = intBuffer.get();
                System.out.println(tmp);

                buffer.clear();
                intBuffer.rewind();

                round--;
                if (round == 0) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}