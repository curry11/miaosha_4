package com.miaosha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miaosha.domain.MiaoshaUser;
import com.miaosha.service.GoodsService;
import com.miaosha.service.MiaoshaUserService;
import com.miaosha.vo.GoodsVo;


@Controller
public class GoodsController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	MiaoshaUserService miaoshauserservice;
	
	@Autowired
	MiaoshaUserService userService;
	
	@RequestMapping("/to_list")             
	public String goodslist(Model model,MiaoshaUser user) {
		model.addAttribute("user",user);
		//查询商品列表
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList",goodsList);
		return "goods_list";
	}
	
	@RequestMapping("/to_detail/{goodsId}")             
	public String detil(Model model,MiaoshaUser user,@PathVariable("goodsId")long goodsId) {
		model.addAttribute("user",user);
		//查询商品详细
		
		GoodsVo goods = goodsService.getGoodsVoBygoodsId(goodsId);
		model.addAttribute("goods",goods);
		long startAT = goods.getStartDate().getTime();
		long endAt = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();
		
		
		int miaoshaStatus = 0 ; //秒杀没有开始为  0   秒杀开始为1   秒杀结束为2
		int remailSeconds = 0;   //倒计时时间
		
		if(now < startAT) {  //秒杀还没有开始，倒计时
			miaoshaStatus = 0;
			remailSeconds = (int)((startAT - now)/1000);
		}else if(now > endAt) { //秒杀已经结束
			miaoshaStatus = 2;
			remailSeconds = -1;
		}else {  //秒杀正在进行
			miaoshaStatus = 1;
			remailSeconds = 0;
		}
		model.addAttribute("status",miaoshaStatus);
		model.addAttribute("remailSeconds",remailSeconds);
		
		return "goods_detail";
	}
}
