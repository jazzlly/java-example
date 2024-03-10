package com.java.examples.net.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ReactorServer {
    private Selector selector;
    private ExecutorService executorService;

    public ReactorServer(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void start() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    log.info("incoming connect ...");
                    ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
                    SocketChannel clientSocket = serverSocket.accept();
                    clientSocket.configureBlocking(false);
                    clientSocket.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    log.info("handler message ...");
                    executorService.execute(new Handler(key));
                }
            }
        }
    }

    private static class Handler implements Runnable {
        private SelectionKey key;

        public Handler(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            try {
                SocketChannel clientSocket = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = clientSocket.read(buffer);
                if (bytesRead == -1) {
                    log.info("close channel ...");
                    clientSocket.close();
                    return;
                }

                buffer.flip();
                // 这里可以添加编解码和业务逻辑
                log.info("write back message ...");
                clientSocket.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        log.info("server start ...");

        ReactorServer server = new ReactorServer(8888);
        server.start();
    }
}
