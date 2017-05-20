package com.corngo.admin.service.impl;

import com.corngo.admin.model.SysMenu;
import com.corngo.admin.mapper.SysMenuMapper;
import com.corngo.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author hanfc
 * @since 2017-05-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    @Override
    public List<SysMenu> selectTree() {
        return baseMapper.selectTree();
    }

    @Override
    public List<SysMenu> getUserFatherMenuList(Long id) {
        return baseMapper.getUserFatherMenuList(id);
    }

}
