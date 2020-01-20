package com.java.examples.file;

import java.io.File;
import java.io.IOException;

public class FileNameTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/tmp/thread. read .write");
        System.out.println(file.getCanonicalPath());
    }
}
