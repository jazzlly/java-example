package com.java.examples.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class EchoClient {

    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("localhost", 8888);
            SocketChannel client = SocketChannel.open(address);
            client.configureBlocking(true);

            ByteBuffer sendBuffer = ByteBuffer.allocate(100);
            ByteBuffer recvBuffer = ByteBuffer.allocate(100);

            while (true) {
                char c = (char) System.in.read();
                System.out.println(c);

                if (c == '\n') {
                    System.out.println("send msg");
                    sendBuffer.put((byte) c);
                    sendBuffer.flip();
                    client.write(sendBuffer);
                    sendBuffer.clear();

                    int n = client.read(recvBuffer);
                    System.out.println("recv msg count: " + n);
                    byte[] bytes = new byte[64];
                    recvBuffer.rewind();
                    recvBuffer.get(bytes);
                    String msg = new String(bytes, Charset.defaultCharset());
                    System.out.println("recv msg: " + msg);
                    recvBuffer.clear();
                    recvBuffer.put(new byte[100]);
                    recvBuffer.clear();

                } else {
                    System.out.println("put char: " + c);
                    sendBuffer.put((byte) c);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}