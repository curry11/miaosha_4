package com.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedispoolFactory {

	@Autowired
	RedisConfig redisconfig;
	
	@Bean
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisconfig.getPoolmaxidle());
		poolConfig.setMaxWaitMillis(redisconfig.getPoolmaxwait()*1000);
		JedisPool jP = new JedisPool(poolConfig,redisconfig.getHost(),redisconfig.getPort(),redisconfig.getTimeout()*1000);
		return jP;
	}
}
