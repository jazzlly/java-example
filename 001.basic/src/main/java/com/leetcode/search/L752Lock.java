package com.leetcode.search;

import java.util.*;

/**
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *
 *
 * 提示：
 *
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 */
public class L752Lock {
    public int openLock1(String[] deadends, String target) {
        // bfs + 路径记录
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        if (visited.contains("0000")) {
            return -1;
        }

        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        queue1.add("0000");
        queue2.add(target);

        int step = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (queue1.size() > queue2.size()) {
                Queue q = queue1;
                queue1 = queue2;
                queue2 = q;
            }

            int size = queue1.size();
            Set<String> tmp = new HashSet<>(queue2);

            for (int i = 0; i < size; i++) {
                String current = queue1.poll();

                if (visited.contains(current)) {
                    continue;
                }

                if (tmp.contains(current)) {
                    return step;
                }

                visited.add(current);
                for (int pos = 0; pos < 4; pos++) {
                    for (int dir = -1; dir <= 1; dir += 2) {
                        String next = flipLock(current, pos, dir);
                        queue1.add(next);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    static String flipLock(String lock, int pos, int direction) {
        StringBuilder sb = new StringBuilder(lock);
        sb.setCharAt(pos, (char) ((sb.charAt(pos) - '0' + 10 + direction) % 10 + '0'));
        return sb.toString();
    }

    public int openLock2(String[] deadends, String target) {        
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) {
            return -1;
        }

        int minStep = 0;

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        visited.add("0000");

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String lock = queue.poll();

                if (target.equals(lock)) {
                    return minStep;
                }

                for (int j = 0; j < 4; j++) {
                    for (int j2 = -1; j2 < 2; j2+=2) {
                        String flip = flipLock(lock, j, j2);
                        if (!visited.contains(flip)) {
                            queue.add(flip);
                            visited.add(flip);
                        }
                    }
                }
            }
            minStep++;
        }

        return -1;
    }

    public int openLock(String[] deadends, String target) {        
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) {
            return -1;
        }

        int minStep = 0;

        Set<String> queue1 = new HashSet<>();
        Set<String> queue2 = new HashSet<>();
        queue1.add("0000");
        queue2.add(target);

        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            Set<String> queue3 = new HashSet<>();
            for (String lock : queue1) {
                visited.add(lock);

                if (queue2.contains(lock)) {
                    return minStep;
                }

                for (int j = 0; j < 4; j++) {
                    for (int j2 = -1; j2 < 2; j2+=2) {
                        String flip = flipLock(lock, j, j2);
                        if (!visited.contains(flip)) {
                            queue3.add(flip);
                        }
                    }
                }
            }

            if (queue3.size() > queue2.size()) {
                queue1 = queue2;
                queue2 = queue3;
            } else {
                queue1 = queue3;
            }
            
            minStep++;
        }

        return -1;
    }


    public static void main(String[] args) {

        L752Lock test = new L752Lock();
        System.out.println(test.openLock(new String[] {"0000"}, "1111"));

        System.out.println(test.openLock(
            new String[] {"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
        System.out.println(test.openLock(
            new String[] {"0201","0101","0102","1212","2002"}, "0202"));
        System.out.println(test.openLock(
            new String[] {"8888"}, "0009"));

        // System.out.println("1".substring(0));
        // System.out.println("1".substring(0,0));

        // System.out.println(flipLock("0000", 0, -1));
        // System.out.println(flipLock("0000", 0, 1));
        // System.out.println(flipLock("0000", 3, -1));
        // System.out.println(flipLock("0000", 3, 1));
    }

}
