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
            // client.configureBlocking(true);

            ByteBuffer sendBuffer = ByteBuffer.allocate(100);
            ByteBuffer recvBuffer = ByteBuffer.allocate(100);

            Thread thread = new Thread(() -> {
                while (true) {
                    int n = 0;
                    try {
                        n = client.read(recvBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    if (n == 0) {
                        continue;
                    }
                    System.out.println("recv msg count: " + n);
                    recvBuffer.flip();
                    byte[] bytes = new byte[n];
                    recvBuffer.get(bytes);
                    recvBuffer.clear();
                    System.out.println(new String(bytes, Charset.defaultCharset()));
                }
            });
            thread.start();

            while (true) {
                char c = (char) System.in.read();
                // System.out.println(c);

                if (c == '\n') {
                    System.out.println("send msg");
                    sendBuffer.put((byte) c);
                    sendBuffer.flip();
                    client.write(sendBuffer);
                    sendBuffer.clear();

//                    int n = client.read(recvBuffer);
////                    while (n == 0) {
////                        n = client.read(recvBuffer);
////                    }
//                    System.out.println("recv msg count: " + n);
//                    recvBuffer.flip();
//                    byte[] bytes = new byte[n];
//                    recvBuffer.get(bytes);
//                    System.out.println(new String(bytes, Charset.defaultCharset()));

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