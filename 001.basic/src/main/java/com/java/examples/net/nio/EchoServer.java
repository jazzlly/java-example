package com.java.examples.net.nio;

import lombok.Builder;
import lombok.Data;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;
import java.io.IOException;

public class EchoServer {

    public static int DEFAULT_PORT = 8888;

    public static void main(String[] args) {

        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }
        System.out.println("Listening for connections on port " + port);

        ServerSocketChannel serverChannel;
        Selector selector;
        try {
            serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        while (true) {
            try {
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(selector,
                                SelectionKey.OP_READ);
                        EchoMsg msg = EchoMsg.builder()
                                .hasData(false)
                                .byteBuffer(ByteBuffer.allocate(100))
                                .build();
                         clientKey.attach(msg);
                    }
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        EchoMsg msg = (EchoMsg) key.attachment();
                        ByteBuffer output = msg.getByteBuffer();
                        output.compact();
                        int n = client.read(output);
                        if (n == 0) {
                            continue;
                        }
                        output.flip();

                        msg.setHasData(true);

                        String tmp = new String(output.array(), 0, output.remaining(),
                                Charset.defaultCharset());
                        System.out.println("recv msg: " + tmp);

                        // 有数据可读时， 才触发写的监听
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    if (key.isWritable()) {
                        System.out.println("write event ...");

                        SocketChannel client = (SocketChannel) key.channel();
                        EchoMsg msg = (EchoMsg) key.attachment();
                        if (!msg.isHasData()) {
                            continue;
                        }

                        ByteBuffer output = msg.getByteBuffer();
                        while (output.hasRemaining()) {
                            String tmp = new String(output.array(), 0, output.remaining(),
                                    Charset.defaultCharset());
                            System.out.println("send msg: " + tmp);
                            client.write(output);
                        }

                        msg.setHasData(false);
                        // 数据写完后， 请清理掉写事件监听
                        key.interestOps(SelectionKey.OP_READ);
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {}
                }
            }
        }
    }
}

@Data
@Builder
class EchoMsg {
    boolean hasData;
    ByteBuffer byteBuffer;
}