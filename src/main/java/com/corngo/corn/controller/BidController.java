package com.corngo.corn.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.service.ISysDeptService;
import com.corngo.admin.service.ISysDictService;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.Responses;
import com.corngo.corn.model.Bid;
import com.corngo.corn.model.Goods;
import com.corngo.corn.service.IBidService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 标的 前端控制器
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@Controller
@RequestMapping("/corn/bid")
public class BidController extends BaseController {

    @Autowired
    private IBidService bidService;
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 列表
     *
     * @return
     * @Author kfc
     * @Date 2017/6/27 19:26
     */
    @RequiresPermissions("corn:goods:list")
    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("bidStatus", JSONObject.toJSONString(sysDictService.initDictMap().get("bid_status")));
        return "corn/bid/bidList";
    }

    /**
     * 异步加载
     *
     * @return
     * @Author kfc
     * @Date 2017/6/27 19:28
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json() {
        EntityWrapper<Bid> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id desc");
        Page<Bid> page = getPage();
        int count = bidService.selectCount(wrapper);
        page.setTotal(count);
        Page<Bid> goodsPage = bidService.selectPage(page);
        return Responses.grid(true, page.getTotal(), page.getCurrent(), goodsPage.getRecords());
    }

}
