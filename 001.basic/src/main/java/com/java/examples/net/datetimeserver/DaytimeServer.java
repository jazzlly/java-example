package com.java.examples.net.datetimeserver;

import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer {

    public final static int PORT = 8888;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    // todo: 如果网络速度慢，或是客户端处理速度慢， 这里是服务端的瓶颈
                    out.write(now.toString() +"\r\n");
                    out.flush();
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}