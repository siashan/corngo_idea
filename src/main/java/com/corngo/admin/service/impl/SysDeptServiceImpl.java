package com.corngo.admin.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.corngo.admin.mapper.SysDeptMapper;
import com.corngo.admin.model.SysDept;
import com.corngo.admin.service.ISysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * SysDept 表数据服务层接口实现类
 *
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {


    @Override
    public List<SysDept> selectTree() {
        return baseMapper.selectTree();
    }
}