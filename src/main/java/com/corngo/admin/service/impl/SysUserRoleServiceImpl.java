package com.corngo.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.corngo.admin.model.SysUser;
import org.springframework.stereotype.Service;

import com.corngo.admin.mapper.SysUserRoleMapper;
import com.corngo.admin.model.SysUserRole;
import com.corngo.admin.service.ISysUserRoleService;

import java.util.List;

/**
 *
 * SysUserRole 表数据服务层接口实现类
 *
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


    @Override
    public List<SysUserRole> findUserRoles(SysUser sysUser) {
        EntityWrapper<SysUserRole> wrapper = new EntityWrapper<SysUserRole>();
        wrapper.where("user_id = {0}",sysUser.getId());
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void deleteByUserId(Long id) {
        baseMapper.deleteByUserId(id);
    }
}