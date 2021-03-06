package com.corngo.corn.service;

import com.corngo.corn.model.Bid;
import com.baomidou.mybatisplus.service.IService;
import com.corngo.corn.model.Goods;

/**
 * <p>
 * 标的 服务类
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
public interface IBidService extends IService<Bid> {

    void createBid(Bid bid, Goods goods);
}
