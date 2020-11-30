package com.leetcode.list;

import java.util.LinkedList;
import java.util.Queue;

public class L286WallsAndGates {
    static int width = 0;
    static int heigth = 0;

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0) {
            return;
        }

        width = rooms[0].length;
        heigth = rooms.length;

        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if (rooms[i][j] == 0) {
                    fillRooms(i, j, rooms);
                }
            }
        }
    }

    static void fillRooms(int x, int y, int[][] rooms) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point point = queue.poll();
                expandRoom(point.up(), rooms, step, queue);
                expandRoom(point.down(), rooms, step, queue);
                expandRoom(point.left(), rooms, step, queue);
                expandRoom(point.right(), rooms, step, queue);
            }
            step++;
        }
    }

    static void expandRoom(Point point, int[][] rooms, int step, Queue queue) {
        // System.out.println("expand room: " + point);
        if (point != null && rooms[point.i][point.j] > step) {
            rooms[point.i][point.j] = step;
            queue.add(point);
        }
    }

    static class Point {
        int i;
        int j;

        public Point(int x, int y) {
            this.i = x;
            this.j = y;
        }

        public Point up() {
            if (i - 1 >= 0) {
                return new Point(i - 1, j);
            }
            return null;
        }

        public Point down() {
            if (i + 1 < heigth) {
                return new Point(i + 1, j);
            }
            return null;
        }

        public Point left() {
            if (j - 1 >= 0) {
                return new Point(i, j - 1);
            }
            return null;
        }

        public Point right() {
            if (j + 1 < width) {
                return new Point(i, j + 1);
            }
            return null;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + i +
                    ", y=" + j +
                    '}';
        }
    }

    public static void main(String[] args) {
//        int[][] rooms = {
//                {max, -1, 0, max},
//                {max, max, max, -1},
//                {max, -1, max, -1},
//                {0, -1, max, max}
//        };

        int[][] rooms = {
                {2147483647,0,2147483647,2147483647,0,2147483647,-1,2147483647}
        };
        L286WallsAndGates test = new L286WallsAndGates();
        test.wallsAndGates(rooms);

        // 0  1  2  3
        //

        System.out.println("haha");
    }
}
