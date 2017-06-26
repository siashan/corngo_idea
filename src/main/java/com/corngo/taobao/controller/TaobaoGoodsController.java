package com.corngo.taobao.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.service.ISysDictService;
import com.corngo.base.support.anno.Token;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.enums.TokenAction;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
import com.corngo.base.support.utils.Responses;
import com.corngo.corn.model.Goods;
import com.corngo.taobao.model.TaobaoGoods;
import com.corngo.taobao.service.ITaobaoGoodsService;
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
 * 淘宝联盟商品表 前端控制器
 * </p>
 *
 * @author hanfc
 * @since 2017-06-02
 */
@Controller
@RequestMapping("/taobao/goods")
public class TaobaoGoodsController  extends BaseController{

    @Autowired
    private ITaobaoGoodsService taobaoGoodsService;
    @Autowired
    private ISysDictService sysDictService;
    /**
     * 列表页面
     *
     * @return
     */
    @RequiresPermissions("taobao:goods:list")
    @RequestMapping("")
    public String list(Model model){
        model.addAttribute("goodsStatus", JSONObject.toJSONString(sysDictService.initDictMap().get("taobao_goods_status")));
        model.addAttribute("goodsType", JSONObject.toJSONString(sysDictService.initDictMap().get("taobao_goods_type")));
        return "taobao/admin/goodsList";
    }

    /**
     * json
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json(TaobaoGoods goods){

        EntityWrapper<TaobaoGoods> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id desc");
        Page<TaobaoGoods> page = getPage();
        int count = taobaoGoodsService.selectCount(wrapper);
        page.setTotal(count);
        Page<TaobaoGoods> goodsPage = taobaoGoodsService.selectPage(page);
        return Responses.grid(true, page.getTotal(), page.getCurrent(), goodsPage.getRecords());
    }

    /**
     * 新增商品
     * @param model
     * @return
     */
    @Token
    @RequiresPermissions("taobao:goods:add")
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        model.addAttribute("goodsStatus",sysDictService.initDict().get("taobao_goods_status"));
        model.addAttribute("goodsType",sysDictService.initDict().get("taobao_goods_type"));
        return "taobao/admin/goodsAdd";
    }

    /**
     * 修改商品
     *
     * @param id
     * @param model
     * @return
     */
    @Token
    @RequiresPermissions("taobao:goods:edit")
    @RequestMapping("toEdit/{id}")
    public String toEdit(@PathVariable Long id ,Model model){
        TaobaoGoods taobaoGoods = taobaoGoodsService.selectById(id);
        model.addAttribute("goods",taobaoGoods);
        model.addAttribute("goodsStatus",sysDictService.initDict().get("taobao_goods_status"));
        model.addAttribute("goodsType",sysDictService.initDict().get("taobao_goods_type"));
        return "taobao/admin/goodsAdd";
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("taobao:goods:del")
    @RequestMapping("del/{id}")
    public Object del(@PathVariable Long id ){
        return taobaoGoodsService.deleteById(id) ? Response.ok("删除成功！") : Response.error("删除失败！");
    }

    /**
     * 保存商品
     * @param taobaoGoods
     * @return
     */
    @Token(value = TokenAction.VALID)
    @ResponseBody
    @RequestMapping("save")
    @RequiresPermissions("taobao:goods:save")
    public Object save(TaobaoGoods taobaoGoods){
        boolean b ;
        if (taobaoGoods.getId() == null ){
            taobaoGoods.setId(IdUtils.oracle(TaobaoGoods.class));
            taobaoGoods.setCreateTime(new Date());
           b =  taobaoGoodsService.insert(taobaoGoods);
        }else {
           b =  taobaoGoodsService.updateById(taobaoGoods);
        }
        return b ? Response.ok("保存成功！") : Response.error("保存失败！");
    }
	
}
