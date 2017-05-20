package com.corngo.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corngo.admin.model.SysUser;

import java.util.List;

/**
 *
 * SysUser 表数据库控制层接口
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    List<String> queryAllPerms(Long id);
}