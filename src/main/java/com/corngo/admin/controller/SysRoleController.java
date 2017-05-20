package com.corngo.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.model.SysRole;
import com.corngo.admin.model.SysRoleMenu;
import com.corngo.admin.model.SysUserRole;
import com.corngo.admin.service.ISysRoleMenuService;
import com.corngo.admin.service.ISysRoleService;
import com.corngo.admin.service.ISysUserRoleService;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 角色控制层
 * <p/>
 * Created by kfc on 2017/2/9.
 */
@Controller
@RequestMapping("admin/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表页
     *
     * @return
     */
    @RequiresPermissions("sys:role:list")
    @RequestMapping("")
    public String toSysRole(Model model) {
        return "admin/sysRole/roleList";
    }

    /**
     * json列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json() {
        EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        Page<SysRole> page = getPage();
        wrapper.orderBy("id ");
        page.setTotal(sysRoleService.selectCount(wrapper));
        return Response.dt(sysRoleService.selectPage(page, wrapper));
    }

    /**
     * 角色新增页
     *
     * @return
     */
    @RequiresPermissions("sys:role:add")
    @RequestMapping("toRoleAdd")
    public String toRoleAdd(Model model) {
        model.addAttribute("treeData", JSONObject.toJSONString(getRoleMenu(null)));
        return "admin/sysRole/roleAdd";
    }


    private Object getRoleMenu(Long roleId) {
        List<Map<String, Object>> list = sysRoleService.getRoleMenu(roleId);
        JSONArray array = new JSONArray();
        if (!list.isEmpty() && list.size() > 0) {
            for (Map<String, Object> map : list) {
                JSONObject json = new JSONObject();
                if (map.get("P").toString().equals("1")) {
                    json.put("checked", true);
                }
                json.put("id", map.get("ID"));
                json.put("name", map.get("NAME"));
                json.put("pId", map.get("PARENTID"));
//                json.put("iconSkin", "s" + map.get("type"));
                array.add(json);
            }
        }
        return array.toJSONString();
    }

    /**
     * 角色修改页
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:edit")
    @RequestMapping("toRoleUpdate/{id}")
    public String toRoleUpdate(@PathVariable Long id, Model model) {
        SysRole sysRole = sysRoleService.selectById(id);
        model.addAttribute("sysRole", sysRole);
        model.addAttribute("treeData", JSONObject.toJSONString(getRoleMenu(id)));
        return "admin/sysRole/roleAdd";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:role:del")
    @RequestMapping("delRole/{id}")
    public Object delRole(@PathVariable Long id) {
        EntityWrapper<SysUserRole> wrapper = new EntityWrapper<>();
        wrapper.where("role_id={0}", id);
        int count = sysUserRoleService.selectCount(wrapper);
        if (count > 0){
            return Response.error("该角色下有关联的用户，不能删除！");
        }
        EntityWrapper<SysRoleMenu> wrapper1 = new EntityWrapper<>();
        wrapper1.where("ROLE_ID = {0}",id);
        sysRoleMenuService.delete(wrapper1);
        return sysRoleService.deleteById(id) ? Response.ok("删除成功！") : Response.error("删除失败！");
    }


    /**
     * 保存角色
     *
     * @param sysRole
     */
    @ResponseBody
    @RequiresPermissions("sys:role:save")
    @RequestMapping("saveRole")
    public Object saveRole(SysRole sysRole, String menus) {
        sysRoleService.saveRole(sysRole, menus);
        return Response.ok("保存成功！");
    }

}
