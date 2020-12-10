package com.leetcode.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class L841CanVisitAllRooms {
    
    int[] visitedRoom = null;   // 遍历过的room集合，防止形成环形图
    Set<Integer> enteredRooms = null; // 进入过的房间的历史

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        enteredRooms = new HashSet<>();
        visitedRoom = new int[rooms.size()];

        return recursion(rooms, 0);
    }
    
    boolean recursion(List<List<Integer>> rooms, int num) {
        enteredRooms.add(num);
        if (enteredRooms.size() == rooms.size()) {
            return true;
        }

        if (visitedRoom[num] == 1) {
            return false;
        }

        visitedRoom[num] = 1;
        List<Integer> keys = rooms.get(num);
        for (Integer key : keys) {
            if (recursion(rooms, key)) {
                return true;
            }
        }

        visitedRoom[num] = 0;
        return false;
    }

    public static void main(String[] args) {
        System.out.println("wahaha");

        L841CanVisitAllRooms test = new L841CanVisitAllRooms();
        assertThat(test.canVisitAllRooms(Arrays.asList(
            Arrays.asList()
            ))).isTrue();

        assertThat(test.canVisitAllRooms(Arrays.asList(
            Arrays.asList(),
            Arrays.asList(0)
            ))).isFalse();

        // [[1],[2],[3],[]]
        assertThat(test.canVisitAllRooms(Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(2),
            Arrays.asList(3),
            Arrays.asList()))).isTrue();

        //  [[1,3],[3,0,1],[2],[0]]
        assertThat(test.canVisitAllRooms(Arrays.asList(
            Arrays.asList(1, 3),        // 0
            Arrays.asList(3, 0, 1),     // 1
            Arrays.asList(2),           // 2
            Arrays.asList(0)))).isFalse(); // 3

        // [[2,3],[],[2],[1,3,1]]
        assertThat(test.canVisitAllRooms(Arrays.asList(
            Arrays.asList(2, 3),  // 0
            Arrays.asList(),      // 1
            Arrays.asList(2),     // 2
            Arrays.asList(1,3,1)))).isTrue();  // 3
    }
}
