package com.miaosha.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;
import com.miaosha.service.MiaoshaUserService;
import com.miaosha.service.UserService;
import com.miaosha.util.ValidatorUtil;
import com.miaosha.vo.LoginVo;

import freemarker.template.utility.StringUtil;

@Controller
public class LoginController {
	
	@Autowired
	MiaoshaUserService miaoshauserservice;
	
	@Autowired
	MiaoshaUserService userService;
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/to_login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/to_lists")
	public String goods(HttpServletResponse response,Model model,
			@CookieValue(value = MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String cookieToken,              //第一个token是从cookie中找，第二个是从参数中找
			@RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String paramToken) {
		//model.addAttribute("user",new MiaoshaUser());
		if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)) {
			return "login";
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;    //如果参数中的token为空则取cookie中的值。反之亦然
		MiaoshaUser user = userService.getByToken(response,token);
		model.addAttribute("user",user);
		return "goods_list";
	}
	
	@RequestMapping("/do_login")
	@ResponseBody                //@Vali 参数校验
	public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginVo loginvo){
		/*
		 * log.info(loginvo.toString()); //参数校验 String passInput =
		 * loginvo.getPassword(); System.out.println(passInput); String mobile =
		 * loginvo.getMobile(); if(StringUtils.isEmpty(passInput)) { return
		 * Result.Error(CodeMsg.PASSWORD_EMPTY); } if(StringUtils.isEmpty(mobile)) {
		 * return Result.Error(CodeMsg.MOBILE_EMPTY); }
		 * if(!ValidatorUtil.isMobile(mobile)) { return
		 * Result.Error(CodeMsg.MOBILE_ERROR); }
		 */
		//登录
		/*
		 * boolean = miaoshauserservice.login(loginvo);
		 *  if(cm.getCode() == 0) {
		 *   return
		 * Result.success(true); 
		 * }else { return Result.Error(cm); }
		 */
		
		miaoshauserservice.login(response,loginvo);
		return Result.success(true);
		
	}
	
	@RequestMapping("/to_listssss")             
	public String detil(Model model,MiaoshaUser user) {
		model.addAttribute("user",user);
		return "goods_list";
	}
	
}
