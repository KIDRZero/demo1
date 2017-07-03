package com.redis.test.redistest;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Test2 {
   public static void main(String[] args) {
	   Jedis jedis=new Jedis("localhost");
	 		System.out.println(jedis.ping());
//	 		jedis.set("kkk", "redis");
//	 		System.out.println("value="+jedis.get("kkk"));
	 		//jedis.lpush("testList", "redis");
	 		List<String> list=jedis.lrange("list1", 0, 10);
	 		for (String ew : list) {
				System.out.println(ew);
			}
	 		//List<String> list1=new ArrayList<String>();
	 		
}
}
