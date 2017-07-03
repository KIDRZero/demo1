package com.pubsub.test;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PubSubDemo {
   public static void main(String[] args) {
	String redisIp="localhost";
	int redisPort=6379;
	 JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisIp, redisPort);
	System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", redisIp, redisPort));

    SubThread subThread = new SubThread(jedisPool);
    subThread.start();

    Publisher publisher = new Publisher(jedisPool);
    publisher.start();
}
}
