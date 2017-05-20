package com.corngo.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.corngo.admin.model.SysDept;
import com.corngo.admin.model.SysUser;
import com.corngo.admin.service.ISysDeptService;
import com.corngo.admin.service.ISysUserService;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
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
 * 组织机构控制层
 *
 * Created by kfc on 2017/2/9.
 */
@Controller
@RequestMapping("admin/sysDept")
public class SysDeptController extends BaseController {

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 组织机构列表页
     *
     * @return
     */
    @RequiresPermissions("sys:dept:list")
    @RequestMapping("")
    public String toSysMenu(Model model){
        List<SysDept> sysDepts = sysDeptService.selectTree();
        model.addAttribute("treedata",sysDepts);
        return "admin/sysDept/deptList";
    }

    /**
     * 组织机构菜单页
     *
     * @return
     */
    @RequiresPermissions("sys:dept:add")
    @RequestMapping("toDeptAdd")
    public String toDeptAdd(Long parentId,Model model){
        model.addAttribute("parentId",parentId);
        return "admin/sysDept/deptAdd";
    }


    /**
     * 组织机构修改页
     *
      * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:dept:edit")
    @RequestMapping("toDeptUpdate/{id}")
    public String toDeptUpdate(@PathVariable Long id,Model model){
        SysDept sysDept = sysDeptService.selectById(id);
        model.addAttribute("sysDept",sysDept);
        return "admin/sysDept/deptAdd";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:dept:del")
    @RequestMapping("delDept/{id}")
    public Object delDept(@PathVariable Long id){
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.where("dept_id={0}", id);
        int count = sysUserService.selectCount(wrapper);
        if (count > 0){
            return Response.error("该部门下有关联的用户，不能删除！");
        }
        return sysDeptService.deleteById(id) ? Response.ok("删除成功！") : Response.error("删除失败！");
    }


    /**
     * 保存菜单
     *
     * @param sysDept
     */
    @ResponseBody
    @RequiresPermissions("sys:dept:save")
    @RequestMapping("saveDept")
    public Object saveDept(SysDept sysDept){
        if (sysDept.getId() == null){
            sysDept.setId(IdUtils.oracle(SysDept.class));
            sysDept.setCreateTime(new Date());
            return sysDeptService.insert(sysDept) ? Response.ok("保存成功！") : Response.error("保存失败！");
        }else {
            return sysDeptService.updateById(sysDept) ? Response.ok("保存成功！") : Response.error("保存失败！");
        }
    }

}
