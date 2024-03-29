package com.miaosha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaosha.dao.GoodsDao;
import com.miaosha.domain.Goods;
import com.miaosha.domain.MiaoshaGoods;
import com.miaosha.vo.GoodsVo;

@Service
public class GoodsService {

	@Autowired
	GoodsDao goodsDao;
	
	@Autowired 
	GoodsService goodsService;
	
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.getGoodsVoList();
	}

	public GoodsVo getGoodsVoBygoodsId(long goodsId) {
		
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	public void reduceStock(GoodsVo goods) {
		
		 MiaoshaGoods g = new MiaoshaGoods(); 
		 g.setGoodsId(goods.getId());
		 goodsDao.reduceStock(g);
	}
}
