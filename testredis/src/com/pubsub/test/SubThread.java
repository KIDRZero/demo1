package com.pubsub.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
 * Jedis��subscribe������������
 * ����һ���߳���ִ��subscribe����
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
	//��jedisPool�л�ȡһ��jedisʵ������ʹ�����ʵ������subscribe����
	//jedis����һ��pubsub������Ϊ��һ��������֮����ܵڶ���������ָ����Ƶ�����ж���
	//��pub/sub�¼�����ʱ�����Զ���������subscriber����
	public void run() {
		  System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
		  Jedis jedis=null;
		  try{
			  jedis=jedisPool.getResource();//��jedis���ӳػ�ȡjedis
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