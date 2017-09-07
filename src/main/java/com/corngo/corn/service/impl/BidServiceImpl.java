package com.corngo.corn.service.impl;

import com.corngo.base.support.PUBConstants;
import com.corngo.corn.mapper.GoodsMapper;
import com.corngo.corn.model.Bid;
import com.corngo.corn.mapper.BidMapper;
import com.corngo.corn.model.Goods;
import com.corngo.corn.service.IBidService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标的 服务实现类
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@Service
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements IBidService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void createBid(Bid bid, Goods goods) {
        baseMapper.insert(bid);
        goods.setLastBidNo(goods.getLastBidNo() + 1);
        goods.setPublishStatus(PUBConstants.GoodsPubStatus.CAN_NOT);
        goodsMapper.updateById(goods);
    }
}
