package com.corngo.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.corngo.admin.model.SysUser;

import java.util.List;

/**
 *
 * SysUser 表数据服务层接口
 *
 */
public interface ISysUserService extends IService<SysUser> {


    List<String> queryAllPerms(Long id);
}