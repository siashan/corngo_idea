package com.corngo.corn.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.service.ISysDictService;
import com.corngo.base.support.PUBConstants;
import com.corngo.base.support.anno.Token;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.enums.TokenAction;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
import com.corngo.base.support.utils.Responses;
import com.corngo.base.support.utils.Shiro;
import com.corngo.corn.model.Bid;
import com.corngo.corn.model.Goods;
import com.corngo.corn.service.IBidService;
import com.corngo.corn.service.IGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@Controller
@RequestMapping("/corn/goods")
public class GoodsController extends BaseController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IBidService bidService;
    /**
     * 商品列表页
     * @return
     */
    @RequiresPermissions("corn:goods:list")
    @RequestMapping("")
    public String list(Model model){
        model.addAttribute("goodsStatus", JSONObject.toJSONString(sysDictService.initDictMap().get("goods_status")));
        model.addAttribute("goodsType", JSONObject.toJSONString(sysDictService.initDictMap().get("goods_type")));
        return "corn/goods/goodsList";
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

    /**
     * 增加商品
     *
     * @return
     */
    @Token
    @RequiresPermissions("corn:goods:add")
    @RequestMapping("toAdd")
    public String addGoods(Model model){
        model.addAttribute("goodsType", sysDictService.initDict().get("goods_type"));
        return "corn/goods/goodsAdd";
    }

    /**
     * 修改商品
     * @param id
     * @param model
     * @return
     */
    @Token
    @RequiresPermissions("corn:goods:edit")
    @RequestMapping("toEdit/{id}")
    public String editGoods(@PathVariable Long id ,Model model){
        model.addAttribute("goodsStatus",sysDictService.initDict().get("goods_status"));
        model.addAttribute("goodsType",sysDictService.initDict().get("goods_type"));
        Goods goods = goodsService.selectById(id);
        model.addAttribute("goods",goods);
        return "corn/goods/goodsAdd";
    }


    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("corn:goods:del")
    @RequestMapping("delGoods/{id}")
    public Object delGoods(@PathVariable Long id){
        boolean b = goodsService.deleteById(id);
        return b ? Response.ok("删除成功！") : Response.error("删除失败！");
    }

    /**
     * 保存商品
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions("corn:goods:save")
    @RequestMapping("save")
    @Token(value = TokenAction.VALID)
    public Object saveGoods(Goods goods){
        boolean b ;
        if (goods.getId() == null){
            goods.setId(IdUtils.oracle(Goods.class));
            goods.setCreateTime(new Date());
            goods.setUserId(Shiro.getUserId());
            goods.setStatus(PUBConstants.GoodsStatus.INIT);
            goods.setLastBidNo(0);
            goods.setPublishStatus(PUBConstants.GoodsPubStatus.CAN);
            b = goodsService.insert(goods);
        }else {

            b = goodsService.updateById(goods);
        }
        return b ? Response.ok("保存成功！") : Response.error("保存失败！");
    }


    /**
     * 发布商品页
     * @param id
     * @return
     * @Author kfc
     * @Date 2017/6/27 14:04
     */
    @ResponseBody
    @RequiresPermissions("corn:goods:toPublishBid")
    @RequestMapping("toPublishBid/{id}")
    public Object toPublishBid(@PathVariable Long id) {
        Goods goods = goodsService.selectById(id);
        if (!PUBConstants.GoodsStatus.PUBLISHED.equals(goods.getStatus())){
            return Response.error("商品还未发布！");
        }
        if (PUBConstants.GoodsPubStatus.CAN_NOT.equals(goods.getPublishStatus())){
            return Response.error("已有标的不能重复发布！");
        }
        Bid bid = new Bid();
        bid.setId(IdUtils.oracle(Bid.class));
        bid.setCreateTime(new Date());
        bid.setBidNo(goods.getLastBidNo() + 1 + "");
        bid.setGoodsId(id);
        bid.setStatus(PUBConstants.BidStatus.INIT);
        bid.setUserId(Shiro.getUserId());
        bid.setAmount(goods.getPrice());
        bid.setReceiveAmount(0.0);
        bid.setTitle(goods.getName()+" 第"+bid.getBidNo()+"期");
        bidService.createBid(bid, goods);
        return Response.ok("发布成功");
    }
	
}
