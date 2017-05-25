package com.corngo.corn.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.model.SysDict;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.Responses;
import com.corngo.corn.model.Goods;
import com.corngo.corn.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author hanfc
 * @since 2017-05-19
 */
@Controller
@RequestMapping("/corn/goods")
public class GoodsController extends BaseController{

    @Autowired
    private IGoodsService goodsService;
    /**
     * 商品列表页
     * @return
     */
    @RequestMapping("")
    public String list(){
        return "goods/goodsList";
    }


    /**
     * json
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json(Goods goods){

        EntityWrapper<Goods> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id desc");
        Page<Goods> page = getPage();
        int count = goodsService.selectCount(wrapper);
        page.setTotal(count);
        Page<Goods> goodsPage = goodsService.selectPage(page);
        return Responses.grid(true, page.getTotal(), page.getCurrent(), goodsPage.getRecords());
    }
	
}
