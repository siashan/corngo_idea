package com.corngo.admin.service;

import com.corngo.admin.model.SysMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author hanfc
 * @since 2017-05-11
 */
public interface ISysMenuService extends IService<SysMenu> {
	

    List<SysMenu> selectTree();

    List<SysMenu> getUserFatherMenuList(Long id);
}
