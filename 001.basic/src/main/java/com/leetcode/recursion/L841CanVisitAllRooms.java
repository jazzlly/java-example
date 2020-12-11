package com.leetcode.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class L841CanVisitAllRooms {
    
    int count = 0;
    boolean[] visited = null;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        count = 0;
        visited = new boolean[rooms.size()];

        recursion(rooms, 0);
        return count == rooms.size();
    }
    
    void recursion(List<List<Integer>> rooms, int num) {
        visited[num] = true;
        count++;

        for (int n : rooms.get(num)) {
            if (!visited[n]) {
                recursion(rooms, n);
            }
        }
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
