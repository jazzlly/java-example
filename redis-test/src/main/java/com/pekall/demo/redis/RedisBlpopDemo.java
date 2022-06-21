package com.pekall.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

public class RedisBlpopDemo {
    public static void main(String[] args) {
        System.out.println("args = " + Arrays.deepToString(args));

        Jedis jedis = new Jedis(args[0], Integer.parseInt(args[1]));
        jedis.auth(args[2]);

        while (true) {
            System.out.println(jedis.blpop(100, "foo"));
        }

//        Jedis jedis = new Jedis("192.168.11.111", 6379);
//        jedis.auth("Pekallrds12#$");
    }
}