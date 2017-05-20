package com.corngo.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corngo.admin.model.SysUserRole;

/**
 *
 * SysUserRole 表数据库控制层接口
 *
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {


    void deleteByUserId(Long id);
}