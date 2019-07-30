package com.miaosha.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaosha.dao.MiaoshaUserDao;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.exception.GlobalException;
import com.miaosha.redis.MiaoshaUserKey;
import com.miaosha.redis.RedisService;
import com.miaosha.result.CodeMsg;
import com.miaosha.util.Md5Util;
import com.miaosha.util.UUIDUtil;
import com.miaosha.vo.LoginVo;

@Service
public class MiaoshaUserService {

	public static final String COOKI_NAME_TOKEN = "token";
	
	@Autowired
	MiaoshaUserDao miaoshauserdao;
	
	@Autowired
	RedisService redisservice;
	
	public MiaoshaUser getById(Long id) {
		return miaoshauserdao.getByid(id);
	}

	public boolean login(HttpServletResponse response,LoginVo loginVo) {
		// TODO Auto-generated method stub
		if(loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String mobile = loginVo.getMobile();
		String formpassword = loginVo.getPassword();
		String formpassT = Md5Util.inputPassFormPass(formpassword);
		System.out.print(formpassT);
		//判断手机号是否存在
		MiaoshaUser user = getById(Long.parseLong(mobile));
		if(user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NULL);
		}
		
		//验证密码
		String dbpass = user.getPassword();
		String saltDB = user.getSalt();
		if(!formpassT.equals(dbpass)) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROE);
		}
		//生成cookie
		String token = UUIDUtil.uuid();
		addCookie(response,token,user);
		return true;
	}
	
	private void addCookie(HttpServletResponse response ,String token,MiaoshaUser user) {
		//传递给客户端
		//把信息存入第三方的缓存中
		redisservice.set(MiaoshaUserKey.token, token, user);
		Cookie cookie = new Cookie(COOKI_NAME_TOKEN,token);
		cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());   //设置cookie的有效期与redis中的token保持一致
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public  MiaoshaUser getByToken(HttpServletResponse response,String token) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		MiaoshaUser user =  redisservice.get(MiaoshaUserKey.token,token,MiaoshaUser.class);
		//延长有效期
		if(user != null) {
			addCookie(response,token, user);
		}
		return user;
	}
}
