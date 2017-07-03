package com.redis.test.redistest;

import redis.clients.jedis.Jedis;
/*
 * 测试字符串
 */
public class RedisStringJava {
    public static void main(String[] args) {
    	Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
	}
}
