package com.corngo.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.corngo.admin.mapper.SysUserMapper;
import com.corngo.admin.model.SysUser;
import com.corngo.admin.service.ISysUserService;

import java.util.List;

/**
 *
 * SysUser 表数据服务层接口实现类
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Override
    public List<String> queryAllPerms(Long id) {
        return baseMapper.queryAllPerms(id);
    }
}