package com.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miaosha.domain.MiaoshaOrder;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.domain.OrderInfo;
import com.miaosha.result.CodeMsg;
import com.miaosha.service.GoodsService;
import com.miaosha.service.MiaoshaService;
import com.miaosha.service.MiaoshaUserService;
import com.miaosha.service.OrderService;
import com.miaosha.vo.GoodsVo;


@Controller
public class MiaoShaController {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	MiaoshaUserService miaoshauserservice;
	
	@Autowired 
	MiaoshaService miaoshaService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/do_miaosha")             
	public String goodslist(Model model,MiaoshaUser user,@RequestParam("goodsId")long goodsId) {
		model.addAttribute("user",user);
		if(user == null) {
			return "login";
		}
		//判断库存
		GoodsVo goods = goodsService.getGoodsVoBygoodsId(goodsId);
		int stock = goods.getStockCount();
		if(stock <= 0) {
			model.addAttribute("errmsg",CodeMsg.GOODS_NULL.getMsg());
			return "miaosha_fail";
		}
		System.out.println("..........."+user.getId()+"..........."+goodsId+""+stock);
		//判断是否已经秒杀到了
		MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
		if(order != null) {
			model.addAttribute("errmsg",CodeMsg.GOODS_NULLs.getMsg());
			return "miaosha_fail";
		}
		//减库存，下订单，写入秒杀订单  通过事务来写
		OrderInfo orderInfo = miaoshaService.maiosha(user,goods);
		model.addAttribute("orderinfo",orderInfo);
		model.addAttribute("goods",goods);
		return "order_detail";
	}
	
}
