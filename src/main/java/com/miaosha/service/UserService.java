package com.miaosha.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miaosha.dao.Userdao;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.domain.User;
import com.miaosha.redis.MiaoshaUserKey;
import com.miaosha.redis.RedisService;

import freemarker.template.utility.StringUtil;

@Service
public class UserService {

	@Autowired
	Userdao userdao;
	
	@Autowired
	RedisService redisService;
	
	public User getByid(int id) {

		return userdao.getByid(id);
	}

	@Transactional   //事务
	public boolean getTx() {
		User u1 = new User();
		u1.setId(4);
		u1.setName("zhangs"); 	
		userdao.insert(u1);
		
		User u2 = new User();
		u2.setId(17);
		u2.setName("zhangssfdsdfsdf");
		userdao.insert(u2);
		
		return true;
	}

	
}
