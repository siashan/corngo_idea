package com.corngo.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.corngo.admin.model.SysUser;
import com.corngo.admin.model.SysUserRole;

import java.util.List;

/**
 *
 * SysUserRole 表数据服务层接口
 *
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    List<SysUserRole> findUserRoles(SysUser sysUser);

    void deleteByUserId(Long id);
}