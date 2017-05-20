package com.corngo.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.model.SysDict;
import com.corngo.admin.service.ISysDictService;
import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.IdUtils;
import com.corngo.base.support.utils.Response;
import com.corngo.base.support.utils.Responses;
import jodd.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据字典控制层
 * <p/>
 * Created by kfc on 2017/2/9.
 */
@Controller
@RequestMapping("admin/sysDict")
public class SysDictController extends BaseController {

    @Autowired
    private ISysDictService sysDictService;

    /**
     * 菜单列表页
     *
     * @return
     */
    @RequiresPermissions("sys:dict:list")
    @RequestMapping("")
    public String toSysMenu() {
        return "admin/sysDict/dictList";
    }

    /**
     * json列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("json")
    public Object json(SysDict sysDict) {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<SysDict>();
        wrapper.orderBy("id desc");
        Page<SysDict> page = getPage();
        if (StringUtil.isNotBlank(sysDict.getDictGroup())) {
            wrapper.where("dict_group={0}", sysDict.getDictGroup());
        }
        if (StringUtil.isNotBlank(sysDict.getDictCode())) {
            wrapper.where("dict_code={0}", sysDict.getDictCode());
        }
        if (StringUtil.isNotBlank(sysDict.getDictName())) {
            wrapper.where("dict_name={0}", sysDict.getDictName());
        }
        int count = sysDictService.selectCount(wrapper);
        page.setTotal(count);
        Page<SysDict> sysDictPage = sysDictService.selectPage(page, wrapper);
        return Responses.grid(true, page.getTotal(), page.getCurrent(), sysDictPage.getRecords());
    }

    /**
     * 新增数据字典页面
     *
     * @return
     */
    @RequiresPermissions("sys:dict:add")
    @RequestMapping("toDictAdd")
    public String toDictAdd() {
        return "admin/sysDict/dictAdd";
    }

    /**
     * 修改数据字典页面
     *
     * @return
     */
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping("toDictUpdate/{id}")
    public String toDictUpdate(@PathVariable Long id, Model model) {
        SysDict sysDict = sysDictService.selectById(id);
        model.addAttribute("sysDict", sysDict);
        return "admin/sysDict/dictAdd";
    }

    /**
     * 保存数据字典
     *
     * @param sysDict
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:dict:save")
    @RequestMapping("saveDict")
    public Object saveDict(SysDict sysDict) {
        if (sysDict.getId() == null) {
            sysDict.setId(IdUtils.oracle(SysDict.class));
            boolean b = sysDictService.insert(sysDict);
            if (b) {
                sysDictService.refresh();
                return Response.ok("保存成功！");
            }
            return Response.error("保存失败！");
        } else {
            boolean b = sysDictService.updateById(sysDict);
            if (b) {
                sysDictService.refresh();
                return Response.ok("保存成功！");
            }
            return Response.error("保存失败！");
        }
    }

    /**
     * 删除数据字典
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:dict:del")
    @RequestMapping("delDict/{id}")
    public Object delDict(@PathVariable Long id) {
        boolean b = sysDictService.deleteById(id);
        if (b) {
            // 刷新缓存
            sysDictService.refresh();
            return Response.ok("删除成功！");
        }
        return Response.error("删除失败！");
    }


}
