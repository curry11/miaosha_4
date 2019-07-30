package com.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miaosha.dao.GoodsDao;
import com.miaosha.domain.Goods;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.domain.OrderInfo;
import com.miaosha.vo.GoodsVo;

@Service
public class MiaoshaService {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	
	@Transactional    //原子事务
	public OrderInfo maiosha(MiaoshaUser user, GoodsVo goods) {
		//减少库存
		goodsService.reduceStock(goods);
		
		//下订单 order_info miaosha_order  写入秒杀订单
		return orderService.creatOrder(user,goods);
		
		
		/*
		 * Goods g = new Goods(); g.setId(goods.getId());
		 * g.setGoodsStock(goods.getGoodsStock()-1); goodsService.reduceStock(g);
		 * 
		 * //下订单 return null;
		 */
	}

}
