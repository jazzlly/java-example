package com.java.examples.file;


import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class NioAsyncFileChannelDemoTest {

    @Test
    public void writeWithFuture() {
        Path path = Paths.get("/tmp/foo");
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("你好x我w123, 逆行！\n".getBytes("utf8"));
            buffer.flip();

            Future<Integer> result =  channel.write(buffer, 0);
            while (!result.isDone()) {
                System.out.println("wait for write file ...");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(result.get());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main thread done!");
    }

    @Test
    public void writeDemo() {
        Path path = Paths.get("/tmp/foo");
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,
                    StandardOpenOption.WRITE, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("你好x我w123, 逆行！\n".getBytes("utf8"));
            buffer.put("你好x我w123, 逆行！\n".getBytes("utf8"));
            buffer.put("你好x我w123, 逆行！\n".getBytes("utf8"));
            buffer.put("你好x我w123, 逆行！\n".getBytes("utf8"));
            buffer.put("你好x我w123, 逆行！".getBytes("utf8"));
            buffer.flip();

            channel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("result: " + result);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (path) {
                        path.notify();
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("main thread sleep 1 second ...");
        synchronized (path) {
            try {
                path.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void readDemo() {
        Path path = Paths.get("/tmp/foo");
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            AsynchronousFileChannel channel =
                    AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("result : " + result);

                    attachment.flip();
                    byte[] bytes = new byte[attachment.remaining()];
                    attachment.get(bytes);
                    try {
                        System.out.println(new String(bytes, "utf8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    synchronized (path) {
                        path.notify();
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("main thread wait ...");
        synchronized (path) {
            try {
                path.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}