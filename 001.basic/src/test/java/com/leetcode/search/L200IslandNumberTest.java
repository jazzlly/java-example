package com.leetcode.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class L200IslandNumberTest {

    @Test
    public void numIslands() {

        char[][] map = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(new L200IslandNumber().numIslands(map));
    }
}