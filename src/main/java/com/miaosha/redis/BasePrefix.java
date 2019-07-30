package com.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {

	private String prefix;    //前缀
	
	private int expireSeconds;  //过期时间
	
	public BasePrefix(int expireSecong,String prefix) {
		this.expireSeconds = expireSecong;
		this.prefix = prefix;
	}
	
	public BasePrefix(String prefix) {  //0代表用不过期
		this(0,prefix);
	}
	
	@Override
	public int expireSeconds() {   //默认0代表用不过期
		
		return expireSeconds;
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		String className = getClass().getSimpleName();   //获取类名
		return className+":"+ prefix;
	}
	
	

}
