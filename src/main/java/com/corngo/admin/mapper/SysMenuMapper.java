package com.corngo.admin.mapper;

import com.corngo.admin.model.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * ${table.comment} Mapper 接口
 * </p>
 *
 * @author hanfc
 * @since 2017-05-11
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectTree();


    List<SysMenu> getUserFatherMenuList(Long id);
}