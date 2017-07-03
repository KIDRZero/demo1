package com.redis.test.redistest;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
/*
 * 测试keys
 */
public class RedisKeyTest {
   public static void main(String[] args) {
	   Jedis jedis = new Jedis("localhost");
       System.out.println("连接成功");

       // 获取数据并输出
       Set<String> keys = jedis.keys("*"); 
       Iterator<String> it=keys.iterator() ;   
       while(it.hasNext()){   
           String key = it.next();   
           System.out.println(key);   
       }
}
}
