package com.corngo.admin.controller;

import com.corngo.base.support.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * webSocket测试控制层
 *
 * Created by kfc on 2017/2/9.
 */
@Controller
@RequestMapping("admin/websocket")
public class WebSocketController extends BaseController {


    /**
     * 菜单列表页
     *
     * @return
     */
    @RequiresPermissions("sys:websocket:list")
    @RequestMapping("")
    public String toSysMenu(Model model){

        return "admin/websocket/socktest";
    }


}
