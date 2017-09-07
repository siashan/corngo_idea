package com.corngo.corn.service.impl;

import com.corngo.corn.model.Goods;
import com.corngo.corn.mapper.GoodsMapper;
import com.corngo.corn.service.IGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	
}
