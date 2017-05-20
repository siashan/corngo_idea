package com.corngo.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corngo.admin.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 *
 * SysRole 表数据库控制层接口
 *
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {


    List<Map<String,Object>> getRoleMenu(Long roleId);

    void deleteByRoleId(Long id);
}