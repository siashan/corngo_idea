package com.corngo.admin.service;


import com.baomidou.mybatisplus.service.IService;
import com.corngo.admin.model.SysDept;

import java.util.List;

/**
 *
 * SysDept 表数据服务层接口
 *
 */
public interface ISysDeptService extends IService<SysDept> {


    List<SysDept> selectTree();
}