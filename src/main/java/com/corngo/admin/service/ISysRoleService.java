package com.corngo.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.corngo.admin.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 *
 * SysRole 表数据服务层接口
 *
 */
public interface ISysRoleService extends IService<SysRole> {


    List<Map<String,Object>> getRoleMenu(Long roleId);

    void saveRole(SysRole sysRole, String menus);

    void updateRole(SysRole sysRole, String menus);

    List<SysRole> selectAll();
}