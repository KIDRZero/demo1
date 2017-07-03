package com.pubsub.test;

import redis.clients.jedis.JedisPubSub;
/*
 * JedisPubSub��������࣬������publish��subscribe�Ļص�����
 * ͨ���̳�JedisPubSub��������ʵ����Щ�ص�����
 * ��pub����sub�¼�����ʱ�������Լ��Ĵ����߼�
 */
public class Subscriber extends JedisPubSub {
	public Subscriber() {
	}

	@Override
	public void onMessage(String channel, String message) {
	    System.out.println(String.format("receive redis published message,channel %s,message %s", channel,message));
	}
    //����
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
	    System.out.println(String.format("subscribe redis published message, channel %s,subscribedChannels %d",channel,subscribedChannels));
	}
    //ȡ������
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		  System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d", 
	                channel, subscribedChannels));
	}

	@Override
	public void onPMessage(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
    
}
