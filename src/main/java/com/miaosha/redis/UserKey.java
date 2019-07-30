package com.miaosha.redis;

public class UserKey extends BasePrefix{

	public UserKey(String prefix) {
		super(prefix);
	}

	
	public static UserKey getById = new UserKey("id");
	public static UserKey getBNmae = new UserKey("name");
}
