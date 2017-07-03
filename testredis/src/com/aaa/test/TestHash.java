package com.aaa.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class TestHash {

	private Jedis jedis;

	private static final String KEY = "hash";

	private static final String FIELD = "name";

	private static final String VALUE = "layman";

	@Before
	public void setUp() {
		this.jedis = new Jedis(new JedisShardInfo("192.168.70.130", 6379));
	}

	/**
	 * HSET key field value ����ϣ�� key �е��� field ��ֵ��Ϊ value �� ��� key
	 * �����ڣ�һ���µĹ�ϣ������������ HSET ������ ����� field �Ѿ������ڹ�ϣ���У���ֵ�������ǡ�
	 * <p/>
	 * HMSET key field value [field value ...] ͬʱ����� field-value (��-ֵ)�����õ���ϣ��
	 * key �С� ������Ḳ�ǹ�ϣ�����Ѵ��ڵ��� ��� key �����ڣ�һ���չ�ϣ��������ִ�� HMSET ������
	 * <p/>
	 * HSETNX key field value ����ϣ�� key �е��� field ��ֵ����Ϊ value �����ҽ����� field �����ڡ�
	 * ���� field �Ѿ����ڣ��ò�����Ч�� ��� key �����ڣ�һ���¹�ϣ��������ִ�� HSETNX ���
	 */
	@Test
	public void HSET() {
		jedis.hset(KEY, FIELD, VALUE);
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD + 0, VALUE + 0);
		map.put(FIELD + 1, VALUE + 1);
		map.put(FIELD + 2, VALUE + 2);
		jedis.hmset(KEY, map);
		HGETALL();
	}

	/**
	 * HGET key field ���ع�ϣ�� key �и����� field ��ֵ��
	 * <p/>
	 * HMGET key field [field ...] ���ع�ϣ�� key �У�һ�������������ֵ�� ����������򲻴����ڹ�ϣ����ô����һ��
	 * nil ֵ�� ��Ϊ�����ڵ� key ������һ���չ�ϣ�����������Զ�һ�������ڵ� key ���� HMGET ����������һ��ֻ���� nil ֵ�ı�
	 * <p/>
	 * HGETALL key ���ع�ϣ�� key �У����е����ֵ�� �ڷ���ֵ�����ÿ������(field
	 * name)֮�������ֵ(value)�����Է���ֵ�ĳ����ǹ�ϣ���С��������
	 */
	@Test
	public void HGETALL() {
		Map<String, String> map = jedis.hgetAll(KEY);
		System.out.println(map);
	}

	/**
	 * HKEYS key ���ع�ϣ�� key �е�������
	 */
	@Test
	public void HKEYS() {
		Set<String> hkeys = jedis.hkeys(KEY);
		System.out.println(hkeys);
	}

	/**
	 * HVALS key ���ع�ϣ�� key ���������ֵ��
	 */
	@Test
	public void HVALS() {
		HKEYS();
		List<String> hvals = jedis.hvals(KEY);
		System.out.println(hvals);
	}

	/**
	 * HEXISTS key field �鿴��ϣ�� key �У������� field �Ƿ���ڡ�
	 */
	@Test
	public void HEXISTS() {
		System.out.println(jedis.hexists(KEY, FIELD));
		System.out.println(jedis.hexists(KEY, FIELD + 4));
	}

	/**
	 * HDEL key field [field ...] ɾ����ϣ�� key �е�һ������ָ���򣬲����ڵ��򽫱����ԡ�
	 */
	@Test
	public void HDEL() {
		HGETALL();
		jedis.hdel(KEY, FIELD + 0);
		HGETALL();
	}

	/**
	 * HINCRBY key field increment Ϊ��ϣ�� key �е��� field ��ֵ�������� increment ��
	 * ����Ҳ����Ϊ�������൱�ڶԸ�������м��������� ��� key �����ڣ�һ���µĹ�ϣ��������ִ�� HINCRBY ��� ����� field
	 * �����ڣ���ô��ִ������ǰ�����ֵ����ʼ��Ϊ 0 �� ��һ�������ַ���ֵ���� field ִ�� HINCRBY ������һ������
	 * ��������ֵ�������� 64 λ(bit)�з������ֱ�ʾ֮�ڡ�
	 * <p/>
	 * HINCRBYFLOAT key field increment Ϊ��ϣ�� key �е��� field ���ϸ��������� increment ��
	 * �����ϣ����û���� field ����ô HINCRBYFLOAT ���Ƚ��� field ��ֵ��Ϊ 0 ��Ȼ����ִ�мӷ������� ����� key
	 * �����ڣ���ô HINCRBYFLOAT ���ȴ���һ����ϣ���ٴ����� field �������ִ�мӷ������� ����������һ����������ʱ������һ������
	 * �� field ��ֵ�����ַ�������(��Ϊ redis �е����ֺ͸����������ַ�������ʽ���棬�������Ƕ������ַ������ͣ� �� field
	 * ��ǰ��ֵ����������� increment ���ܽ���(parse)Ϊ˫���ȸ�����(double precision floating point
	 * number)
	 */
	@Test
	public void HINCRBY() {
		jedis.hset(KEY, FIELD + 0, "0");
		HGETALL();
		jedis.hincrBy(KEY, FIELD + 0, 24);
		HGETALL();
	}

	/**
	 * HLEN key ���ع�ϣ�� key �����������
	 */
	@Test
	public void HLEN() {
		System.out.println(jedis.hlen(KEY));
	}

	/**
	 * HSTRLEN key field 3.2�Ժ���� ���ع�ϣ�� key �У� ������� field �������ֵ���ַ������ȣ�string
	 * length���� ��������ļ������򲻴��ڣ� ��ô����� 0 ��
	 */
	@Test
	public void HSTRLEN() {
	}

}
