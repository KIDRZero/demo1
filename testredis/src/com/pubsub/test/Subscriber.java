package com.pubsub.test;

import redis.clients.jedis.JedisPubSub;
/*
 * JedisPubSub这个抽象类，定义了publish，subscribe的回调方法
 * 通过继承JedisPubSub类来重新实现这些回调方法
 * 当pub或者sub事件发生时，定制自己的处理逻辑
 */
public class Subscriber extends JedisPubSub {
	public Subscriber() {
	}

	@Override
	public void onMessage(String channel, String message) {
	    System.out.println(String.format("receive redis published message,channel %s,message %s", channel,message));
	}
    //订阅
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
	    System.out.println(String.format("subscribe redis published message, channel %s,subscribedChannels %d",channel,subscribedChannels));
	}
    //取消订阅
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
