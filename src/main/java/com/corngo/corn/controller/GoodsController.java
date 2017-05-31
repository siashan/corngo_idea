package com.corngo.corn.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.service.ISysDeptService;
import com.corngo.admin.service.ISysDictService;
import com.corngo.base.support.PUBConstants;
import com.corngo.base.support.anno.Token;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.enums.TokenAction;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
import com.corngo.base.support.utils.Responses;
import com.corngo.base.support.utils.Shiro;
import com.corngo.corn.model.Goods;
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
 * @since 2017-05-19
 */
@Controller
@RequestMapping("/corn/goods")
public class GoodsController extends BaseController{

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISysDictService sysDictService;
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
        model.addAttribute("goodsType",sysDictService.initDict().get("goods_type"));
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
            b = goodsService.insert(goods);
        }else {

            b = goodsService.updateById(goods);
        }
        return b ? Response.ok("保存成功！") : Response.error("保存失败！");
    }
	
}
