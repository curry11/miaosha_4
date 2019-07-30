package com.miaosha.redis;

public class OrderKey extends BasePrefix{

	public OrderKey(int expireSecong, String prefix) {
		super(expireSecong, prefix);
		
	}

}
