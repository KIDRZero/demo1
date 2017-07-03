package com.pubsub.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
 * Jedis的subscribe操作是阻塞的
 * 另起一个线程来执行subscribe操作
 */
public class SubThread extends Thread{
    private final JedisPool jedisPool;
    private final Subscriber subscriber=new Subscriber();
    private final String channel="mychannel";
	public SubThread(JedisPool jedisPool) {
		super("SubThread");
		this.jedisPool = jedisPool;
	}
	@Override
	//从jedisPool中获取一个jedis实例，并使用这个实例进行subscribe操作
	//jedis接受一个pubsub对象作为第一个参数，之后接受第二个参数，指定对频道进行订阅
	//当pub/sub事件发生时，会自动调用我们subscriber方法
	public void run() {
		  System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
		  Jedis jedis=null;
		  try{
			  jedis=jedisPool.getResource();//从jedis连接池获取jedis
			  jedis.subscribe(subscriber, channel);
		  }catch(Exception e){
			   System.out.println(String.format("subsrcibe channel error, %s", e));
		  }finally{
			  if(jedis!=null){
				  jedis.close();
			  }
		  }
	}
    
}
