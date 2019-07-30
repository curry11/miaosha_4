package com.miaosha.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//加载redis配置文件
@Component
@ConfigurationProperties(prefix="spring.redis")
public class RedisConfig {

	private String host;
	private int port;
	private int timeout;
	private int poolmaxwait;
	private int poolmaxidle;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getPoolmaxwait() {
		return poolmaxwait;
	}
	public void setPoolmaxwait(int poolmaxwait) {
		this.poolmaxwait = poolmaxwait;
	}
	public int getPoolmaxidle() {
		return poolmaxidle;
	}
	public void setPoolmaxidle(int poolmaxidle) {
		this.poolmaxidle = poolmaxidle;
	}
}
