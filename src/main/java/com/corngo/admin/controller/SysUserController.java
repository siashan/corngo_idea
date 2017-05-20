package com.corngo.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.model.SysUser;
import com.corngo.admin.model.SysUserRole;
import com.corngo.admin.service.*;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
import jodd.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户控制层
 */
@Controller
@RequestMapping("admin/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 用戶列表页
     *
     * @return
     */
    @RequiresPermissions("sys:user:list")
    @RequestMapping("")
    public String toSysUser(Model model) {
        model.addAttribute("userStatus", JSONObject.toJSONString(sysDictService.initDictMap().get("user_status")));
        model.addAttribute("status", sysDictService.initDict().get("user_status"));
        return "admin/sysUser/userList";
    }

    /**
     * 新增用户页
     *
     * @return
     */
    @RequiresPermissions("sys:user:add")
    @RequestMapping("toAdd")
    public String toUserAdd(Long parentId, Model model) {
        model.addAttribute("gender", sysDictService.initDict().get("gender"));
        model.addAttribute("status", sysDictService.initDict().get("user_status"));
        model.addAttribute("deptList", sysDeptService.selectTree());
        model.addAttribute("roleList", sysRoleService.selectAll());
        return "admin/sysUser/userAdd";
    }


    /**
     * 用户修改页
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:user:edit")
    @RequestMapping("toUpdate/{id}")
    public String toUserUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("gender", sysDictService.initDict().get("gender"));
        SysUser sysUser = sysUserService.selectById(id);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("deptList", sysDeptService.selectTree());
        model.addAttribute("roleList", sysRoleService.selectAll());
        model.addAttribute("userRoles", getUserRoles(sysUser));
        model.addAttribute("status", sysDictService.initDict().get("user_status"));
        return "admin/sysUser/userAdd";
    }

    private String getUserRoles(SysUser sysUser) {
        List<SysUserRole> list = sysUserRoleService.findUserRoles(sysUser);
        if (!list.isEmpty() && list.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (SysUserRole sur : list) {
                sb.append(sur.getRoleId() + ",");
            }
            String str = sb.toString();
            return str.substring(0, str.lastIndexOf(","));
        }
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:user:del")
    @RequestMapping("delUser/{id}")
    public Object delUser(@PathVariable Long id) {
        return sysUserService.deleteById(id) ? Response.ok("删除成功！") : Response.error("删除失败！");
    }


    /**
     * 保存用户
     *
     * @param sysUser
     */
    @ResponseBody
    @RequiresPermissions("sys:user:save")
    @RequestMapping("saveUser")
    public Object saveUser(SysUser sysUser, String roleList) {
        if (sysUser.getId() == null) {
            sysUser.setId(IdUtils.oracle(SysUser.class));
            sysUser.setStatus("0");// 用户状态默认可用
            String pwd = new Sha256Hash(sysUser.getPassword()).toHex();
            sysUser.setPassword(pwd);
            saveRole(sysUser, roleList);
            return sysUserService.insert(sysUser) ? Response.ok("保存成功！") : Response.error("保存失败！");
        } else {
            saveRole(sysUser, roleList);
            return sysUserService.updateById(sysUser) ? Response.ok("保存成功！") : Response.error("保存失败！");
        }

    }

    private void saveRole(SysUser user, String roleList) {
        // 删除用户原有的角色，重新分配
        sysUserRoleService.deleteByUserId(user.getId());
        String[] roles = roleList.split(",");
        for (String roleId : roles) {
            Long rId = Long.parseLong(roleId);
            SysUserRole sur = new SysUserRole();
            sur.setId(IdUtils.oracle(SysUserRole.class));
            sur.setRoleId(rId);
            sur.setUserId(user.getId());
            sysUserRoleService.insert(sur);
        }
    }

    /**
     * json
     * @param sysUser
     * @param startTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json(SysUser sysUser,String startTime,String endTime) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        wrapper.orderBy("create_time desc");
        if (StringUtil.isNotBlank(startTime)){
            wrapper.where("to_char(create_time,'yyyy-mm-dd hh24:mi:ss') >= '"+startTime+"'");
        }
        if (StringUtil.isNotBlank(endTime)){
            wrapper.where("to_char(create_time,'yyyy-mm-dd hh24:mi:ss') <= '" + endTime+"'");
        }
        if (StringUtil.isNotBlank(sysUser.getUserName())){
            wrapper.like("user_name", sysUser.getUserName());
        }
        if (StringUtil.isNotBlank(sysUser.getRealName())){
            wrapper.like("real_name",sysUser.getRealName());
        }
        if (StringUtil.isNotBlank(sysUser.getPhoneNo())){
            wrapper.where("phone_no = {0}",sysUser.getPhoneNo());
        }
        if (StringUtil.isNotBlank(sysUser.getStatus())){
            wrapper.where("status = {0}",sysUser.getStatus());
        }

        Page<SysUser> page = getPage();
        page.setTotal(sysUserService.selectCount(wrapper));
        return Response.dt(sysUserService.selectPage(page, wrapper));
    }

}