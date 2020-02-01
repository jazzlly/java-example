package com.java.examples.file;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class NioPathDemoTest {

    @Test
    public void demo() {
        Path path = Paths.get("/tmp");
        Path file = Paths.get("/tmp/random1");
        Path relPath = Paths.get("/tmp", "random2");

        System.out.println(path.toString());
        System.out.println(file.toString());
        System.out.println(path.toAbsolutePath().toString());
        System.out.println(relPath.toAbsolutePath().toString());

        System.out.println(Paths.get(".").toAbsolutePath());
        System.out.println(Paths.get("").toAbsolutePath());

        // normalize removes all . and ..
        assertThat(
                Paths.get("/tmp/./../tmp/random1").normalize().toString())
                .isEqualTo("\\tmp\\random1");

        System.out.println(file.getFileName());
    }


    @Test
    public void iterateDir() {
        printDir(new File("/tmp"), "");
    }

    void printDir(File root, String prefix) {
        if (!root.isDirectory() && !root.isFile()) {
            return;
        }

        System.out.print(prefix);
        System.out.println(root.getName());

        if (root.isDirectory()) {
            File[] children = root.listFiles();
            for (File child : children) {
                printDir(child, prefix + "  ");
            }
        }
    }
}