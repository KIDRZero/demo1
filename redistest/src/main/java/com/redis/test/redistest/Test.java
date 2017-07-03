package com.redis.test.redistest;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class Test {
   public static void main(String[] args) throws InterruptedException {
	   Jedis jedis=new Jedis("localhost");
		System.out.println(jedis.ping());
		jedis.set("kkk", "redis");
		System.out.println("value="+jedis.get("kkk"));
		jedis.lpush("testList", "redis");
		List<String> list=jedis.lrange("testList", 0, 5);
		//List<String> list1=new ArrayList<String>();
		for(int i=0;i<5000;i++){
			jedis.lpush("list1", String.valueOf(i));
			Thread.sleep(1000);
			System.out.println(i);
		}
}
}
