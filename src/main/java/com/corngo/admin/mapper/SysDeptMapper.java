package com.corngo.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corngo.admin.model.SysDept;

import java.util.List;

/**
 *
 * SysDept 表数据库控制层接口
 *
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {


    List<SysDept> selectTree();
}