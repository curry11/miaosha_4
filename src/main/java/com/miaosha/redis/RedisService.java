package com.miaosha.redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

	@Autowired
	JedisPool jedispool;
	
	@Autowired
	RedisConfig redisconfig;
	
	//获取单个对象
	public <T> T get(KeyPrefix prefix,String key,Class<T>clazz){
		Jedis jedis = null;
		try {
			jedis = jedispool.getResource();
			//生成正真的key
			String realkey = prefix.getPrefix()+key;
			String str = jedis.get(realkey);
			T t = stringToBena(str,clazz);
			return t;
		} finally {
			// TODO: handle finally clause
			reteurnTopool(jedis);
		}
	}
	
	//设置对象
	public <T> Boolean set(KeyPrefix prefix,String key,T value){
		Jedis jedis = null;
		try {
			jedis = jedispool.getResource();
			String str = beanToString(value);
			if(str==null || str.length()<=0) {
				return false;
			}
			//生成正真的key
			String realkey = prefix.getPrefix()+key;
			//设置Key过期时间
			int seconds = prefix.expireSeconds();
			if(seconds <= 0) {
				jedis.set(realkey,str);
			}else {
				jedis.setex(realkey, seconds, str);
			}
			return true;
		} finally {
			// TODO: handle finally clause
			reteurnTopool(jedis);
		}
	}
	
	//增加值
	public <T> Long incr(KeyPrefix prefix,String key,Class<T>clazz){
		Jedis jedis = null;
		try {
			jedis = jedispool.getResource();
			//生成正真的key
			String realkey = prefix.getPrefix()+key;
			return jedis.incr(realkey);
		} finally {
			// TODO: handle finally clause
			reteurnTopool(jedis);
		}
	}
	
	//减少值
	public <T> Long decr(KeyPrefix prefix,String key,Class<T>clazz){
		Jedis jedis = null;
		try {
			jedis = jedispool.getResource();
			//生成正真的key
			String realkey = prefix.getPrefix()+key;
			return jedis.decr(realkey);
		} finally {
			// TODO: handle finally clause
			reteurnTopool(jedis);
		}
	}
	
	//判读Kye是否存在
	public <T> Boolean exist(KeyPrefix prefix,String key,Class<T>clazz){
		Jedis jedis = null;
		try {
			jedis = jedispool.getResource();
			//生成正真的key
			String realkey = prefix.getPrefix()+key;
			return jedis.exists(realkey);
		} finally {
			// TODO: handle finally clause
			reteurnTopool(jedis);
		}
	}
	
	private <T> String beanToString(T value) {
		if(value==null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz==Integer.class) {
			return ""+value;
		}else if(clazz == String.class) {
			return (String)value;
		}else if(clazz == long.class || clazz ==Long.class) {
			return ""+value;
		}else {
			return JSON.toJSONString(value);
		}
		
	}

	private <T> T stringToBena(String str, Class<T>clazz) {
		if(str == null || str.length()<=0 || clazz ==null) {
			return null;
		}
		if(clazz == int.class || clazz==Integer.class) {
			return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			return (T)str;
		}else if(clazz == long.class || clazz ==Long.class) {
			return (T)Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
		
	}

	private void reteurnTopool(Jedis jedis) {
		if(jedis != null) {
			jedis.close();
		}
	}
	
	
}
