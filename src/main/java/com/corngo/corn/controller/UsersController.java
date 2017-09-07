package com.corngo.corn.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.model.SysUser;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.Response;
import com.corngo.base.support.utils.Responses;
import com.corngo.corn.model.Users;
import com.corngo.corn.service.IUsersService;
import jodd.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hanfc
 * @since 2017-06-26
 */
@Controller
@RequestMapping("/corn/users")
public class UsersController extends BaseController{
    @Autowired
    private IUsersService usersService;

    /**
     * 用户列表
     *
     * @return
     * @Author kfc
     * @Date 2017/9/7 9:28
     */
    @RequestMapping("")
    @RequiresPermissions("corn:user:list")
    public String list(){
        return "corn/users/userList";
    }

    /**
     *
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json(Users users,String startTime,String endTime){
        EntityWrapper<Users> wrapper = new EntityWrapper<Users>();
        wrapper.orderBy("create_time desc");
        if (StringUtil.isNotBlank(startTime)){
            wrapper.where("to_char(create_time,'yyyy-mm-dd hh24:mi:ss') >= '"+startTime+"'");
        }
        if (StringUtil.isNotBlank(endTime)){
            wrapper.where("to_char(create_time,'yyyy-mm-dd hh24:mi:ss') <= '" + endTime + "'");
        }
        if (StringUtil.isNotBlank(users.getName())){
            wrapper.like("name", users.getName());
        }

        if (StringUtil.isNotBlank(users.getMobile())){
            wrapper.where("MOBILE = {0}",users.getMobile());
        }

        Page<Users> page = getPage();
        page.setTotal(usersService.selectCount(wrapper));
        Page<Users> sysDictPage = usersService.selectPage(page, wrapper);
        return Responses.grid(true, page.getTotal(), page.getCurrent(), sysDictPage.getRecords());
    }


}
