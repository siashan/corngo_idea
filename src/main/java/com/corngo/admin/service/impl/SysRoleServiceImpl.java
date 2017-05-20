package com.corngo.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.corngo.admin.mapper.SysRoleMenuMapper;
import com.corngo.admin.model.SysRoleMenu;
import com.corngo.base.support.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corngo.admin.mapper.SysRoleMapper;
import com.corngo.admin.model.SysRole;
import com.corngo.admin.service.ISysRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SysRole 表数据服务层接口实现类
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<Map<String, Object>> getRoleMenu(Long roleId) {
        return baseMapper.getRoleMenu(roleId);
    }


    @Override
    public void saveRole(SysRole sysRole, String menus) {
        if (sysRole.getId() == null) {
            sysRole.setId(IdUtils.oracle(SysRole.class));
            baseMapper.insert(sysRole);
        } else {
            baseMapper.updateById(sysRole);
        }
        savePrivileges(sysRole, menus);
    }

    @Override
    public void updateRole(SysRole sysRole, String menus) {

    }

    @Override
    public List<SysRole> selectAll() {
        EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        wrapper.orderBy("id");
       return baseMapper.selectList(wrapper);
    }

    private void savePrivileges(SysRole role, String privileges) {
        baseMapper.deleteByRoleId(role.getId());
        String[] ps = privileges.split(",");
        ArrayList list = Lists.newArrayList();
        for (String s : ps) {
            SysRoleMenu srr = new SysRoleMenu();
            srr.setRoleId(role.getId());
            srr.setId(IdUtils.oracle(SysRoleMenu.class));
            srr.setMenuId(Long.parseLong(s));
            list.add(srr);
            sysRoleMenuMapper.insert(srr);
        }
//        sysRoleMenuMapper.insertBatch(list);
//        sysRoleMenuMapper.i
    }

}