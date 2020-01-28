package com.java.examples.net.simpleHttpServer;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BasicHttpServerTest {
    private static int nThreads = Runtime.getRuntime().availableProcessors();
    private static ExecutorService taskExecutor =
            new ThreadPoolExecutor(nThreads, nThreads, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100),
                    new ThreadPoolExecutor.AbortPolicy());

    private static class ClientThread implements Runnable {
        private final String threadId;
        DefaultHttpClient httpclient = new DefaultHttpClient();

        public ClientThread(String threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                HttpGet httpGet = new HttpGet("http://localhost:8888");
                try {
                    HttpResponse response1 = httpclient.execute(httpGet);

                    System.out.println("status line");
                    System.out.println(response1.getStatusLine());

                    Header[] headers = response1.getAllHeaders();
                    System.out.println("headers:");
                    for (Header h : headers) {
                        System.out.println(h.toString());
                    }

                    // Get the response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(
                            response1.getEntity().getContent()));

                    String line = "";
                    System.out.println("entity:");
                    while ((line = rd.readLine()) != null) {
                        System.out.println(line);
                    }

                    HttpEntity entity1 = response1.getEntity();
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    // FIXME:
                    // EntityUtils.consume(entity1);
                } catch (ClientProtocolException e) {
                    log.warn("", e);
                } catch (IOException e) {
                    log.warn("", e);
                } finally {
                    // FIXME:
                    // httpGet.releaseConnection();
                }

                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    log.warn("", e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            ClientThread clientThread = new ClientThread("" + i);
            taskExecutor.submit(clientThread);
        }
        Thread.sleep(120_000);
        taskExecutor.shutdown();
    }
}
