package com.leetcode.division;


import java.util.HashMap;
import java.util.Map;

public class L50Pow2 {


    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * @param x
     * @param n
     * @return
     */
//    public static double myPow2(double x, int n) {
//        if (x == 0.0 || x == 1.0) {
//            return x;
//        }
//
//        if (n == 0) {
//            return 1;
//        }
//        if (n == 1) {
//            return x;
//        }
//        if (n == -1) {
//            return 1/x;
//        }
//
//        if (map.containsKey(n)) {
//            return map.get(n);
//        }
//
//        double result;
//        if (n % 2 == 0) {
//            result = myPow(x, n/2) * myPow(x, n/2);
//
//        } else {
//            double tmp = n > 0 ? x : 1/x;
//            result =  myPow(x, n/2) * myPow(x, n/2) * tmp;
//        }
//
//        map.put(n, result);
//        return result;
//    }

    public Map<Integer, Double> map = new HashMap<>();


    public double myPow(double x, int n) {
        if (x == 0.0 || x == 1.0) {
            return x;
        }

        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            x = 1.0/x;
        }

        return recursion(x, n);
    }

    public double recursion(double x, int n) {
        if (n == 1 || n == -1) {
            return x;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        double result;
        double tmp = recursion(x, n/2);
        result = tmp * tmp;
        if (n % 2 != 0) {
            result *= x;
        }

        map.put(n, result);
        return result;
    }
}
