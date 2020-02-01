package com.java.examples.file;


import org.junit.Test;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class NioFilesDemoTest {

    @Test
    public void fileExists() {
        Path path = Paths.get("/tmp");
        assertThat(Files.exists(path)).isTrue();

        Path notExist = Paths.get("/ttmmpp");
        assertThat(Files.exists(notExist)).isFalse();
    }

    @Test
    public void createDir() {
        Path path = Paths.get("/tmp", UUID.randomUUID().toString());
        assertThat(Files.exists(path)).isFalse();

        try {
            Files.createDirectory(path);
            assertThat(Files.exists(path)).isTrue();

            Files.deleteIfExists(path);
            assertThat(Files.exists(path)).isFalse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void copy() {
        try (FileWriter writer = new FileWriter("/tmp/foo")){
            writer.write("wahaha哇哈哈哈\n");
            writer.write("嘻嘻嘻喵喵\n");
            writer.write("1234");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

//        try {
//            Files.deleteIfExists(Paths.get("/tmp/bar"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Path src = Paths.get("/tmp/foo");
        Path dest = Paths.get("/tmp/bar");
        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("/tmp/bar"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void walkDir() {
        try {
            Files.walkFileTree(Paths.get("/tmp"), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("pre visit dir: " + dir.toAbsolutePath().toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file.toAbsolutePath().toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("post visit dir: " + dir.toAbsolutePath().toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void walkDir1() {
        try {
            Files.walkFileTree(Paths.get("/tmp"), new FileVisitor<Path>() {
                String prefix = "";

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println(prefix + dir.getFileName().toString());
                    prefix = prefix + "  ";
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(prefix + file.getFileName().toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    prefix = prefix.substring(0, prefix.length() - 2);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}