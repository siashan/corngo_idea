package com.corngo.admin.controller;

import com.corngo.admin.model.SysMenu;
import com.corngo.admin.model.SysUser;
import com.corngo.admin.service.ISysDictService;
import com.corngo.admin.service.ISysMenuService;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
import com.corngo.base.support.utils.Shiro;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 菜单控制层
 *
 * Created by kfc on 2017/2/9.
 */
@Controller
@RequestMapping("admin/sysMenu")
public class MenuController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 菜单列表页
     *
     * @return
     */
    @RequiresPermissions("sys:menu:list")
    @RequestMapping("")
    public String toSysMenu(Model model){
        List<SysMenu> sysMenus = sysMenuService.selectTree();
        model.addAttribute("treedata",sysMenus);
        SysUser user = Shiro.getUser();
        System.out.println(user.getId());
        return "admin/sysMenu/menuList";       
    }

    /**
     * 新增菜单页
     *
     * @return
     */
    @RequiresPermissions("sys:menu:add")
    @RequestMapping("toMenuAdd")
    public String toMenuAdd(Long parentId,Model model){
        model.addAttribute("menuType",sysDictService.initDict().get("menu_type"));
        model.addAttribute("parentId",parentId);
        return "admin/sysMenu/menuAdd";
    }


    /**
     * 菜单修改页
     *
      * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping("toMenuUpdate/{id}")
    public String toMenuUpdate(@PathVariable Long id,Model model){
        SysMenu sysMenu = sysMenuService.selectById(id);
        model.addAttribute("menuType",sysDictService.initDict().get("menu_type"));
        model.addAttribute("sysMenu",sysMenu);
        return "admin/sysMenu/menuAdd";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:del")
    @RequestMapping("delMenu/{id}")
    public Object delMenu(@PathVariable Long id){
        return sysMenuService.deleteById(id) ? Response.ok("删除成功！") : Response.error("删除失败！");
    }


    /**
     * 保存菜单
     *
     * @param sysMenu
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:save")
    @RequestMapping("saveMenu")
    public Object saveMenu(SysMenu sysMenu){
        if (sysMenu.getId() == null){
            sysMenu.setId(IdUtils.oracle(SysMenu.class));
            sysMenu.setCreateTime(new Date());
            return sysMenuService.insert(sysMenu) ? Response.ok("保存成功！") : Response.error("保存失败！");
        }else {
            return sysMenuService.updateById(sysMenu) ? Response.ok("保存成功！") : Response.error("保存失败！");
        }
    }

}
