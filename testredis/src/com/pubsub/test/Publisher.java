package com.pubsub.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/*
 * ����������������û������룬�������뷢����channel��
 * ���û�����quit���������
 */
public class Publisher {
	private final JedisPool jedisPool;

	public Publisher(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
    public void start(){
    	BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    	Jedis jedis=jedisPool.getResource();
    	while(true){
    		String line=null;
    		try{
    			line=reader.readLine();
    			if(!"quit".equals(line)){
    				jedis.publish("mychannel", line);
    			}else{
    				break;
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    }
}
