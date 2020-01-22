package com.java.examples.net.simpleHttpServer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;

@Slf4j
public class BasicHttpServer {
    private static ExecutorService bootstrapExecutor =
            Executors.newSingleThreadExecutor();
    private static ExecutorService taskExecutor;
    private static int PORT = 8888;

    static void startHttpServer() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        taskExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100),
                        new ThreadPoolExecutor.DiscardPolicy());
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(5000);
            log.info("connection on port: {}", serverSocket.getLocalPort());
            bootstrapExecutor.submit(new ServerThread(serverSocket));
        } catch (Exception e) {
            log.warn("", e);
        }
    }

    private static class ServerThread implements Runnable {

        private ServerSocket serverSocket;

        public ServerThread(ServerSocket s) throws IOException {
            this.serverSocket = s;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket socket = this.serverSocket.accept();
                    HttpTask eventTask = new HttpTask(socket);
                    taskExecutor.submit(eventTask);
                } catch (SocketTimeoutException e) {
                    System.out.println("accept timeout!");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BasicHttpServer.startHttpServer();
        Thread.sleep(120_000);
    }
}
