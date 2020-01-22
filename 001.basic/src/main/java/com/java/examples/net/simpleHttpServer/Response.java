package com.java.examples.net.simpleHttpServer;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private String version;
    private int code;
    private String status;

    private Map<String, String> headers;

    private String message;
}