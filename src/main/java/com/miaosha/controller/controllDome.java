package com.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.domain.User;
import com.miaosha.redis.RedisService;
import com.miaosha.redis.UserKey;
import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;
import com.miaosha.service.UserService;


@Controller
@RequestMapping("/a")
public class controllDome {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	RedisService redisservice;
	
	//正常输出
	@RequestMapping("/name")
	@ResponseBody
	public  String Names() {	
		return "hhhhhh";
	}
	
	//添加了result输出
	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> hello() {
		
		return Result.success("hello.imocc");
	}
	
//	添加错误输出
	@RequestMapping("/helloError")
	@ResponseBody
	Result<String> helloError() {
		
		return Result.Error(CodeMsg.SERVER_ERROR);
	}
	
//	跳转页面
	@RequestMapping("/ss")
	public String ss(Model model) {
		model.addAttribute("name","json");
		return "hello";
	}
	
//	跳转页面
	@RequestMapping(value = "/hellos")
	public String hellos(){
		System.out.println("Hello");
		return "Log";
	}
	
//	跳转页面
	@RequestMapping(value = "/db/get")
	public String helloss(){
		System.out.println("Hello");
		return "Log";
	}
	
//	连接数据库进行查询
	@RequestMapping("/aa")
	@ResponseBody
	public Result<User> getuer() {
		System.out.print(userservice.getByid(1));
		User user = userservice.getByid(1);
		return Result.success(user);
	}
	
//	连接数据库进行插入
	@RequestMapping("/aa/tex")
	@ResponseBody
	public Result<Boolean> getuertx() {
		System.out.print(userservice.getByid(1));
		 userservice.getTx();
		return Result.success(true);
	}
	
//	从redis中获取数据
	@RequestMapping("/aa/get")
	@ResponseBody
	public Result<User> redisGet() {
		User v1 = redisservice.get(UserKey.getById,""+1,User.class);
		return Result.success(v1);
	}
	
//	向redis中插入数据
	@RequestMapping("/aa/set")
	@ResponseBody
	public Result<Boolean> redisSet() {
		User user = new User();
		user.setId(1);
		user.setName("11111");
		redisservice.set(UserKey.getById,""+1,user);
		return Result.success(true);
	}
}
