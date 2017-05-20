package com.corngo.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.corngo.admin.model.SysDict;


import java.util.Map;

/**
 *
 * SysDict 表数据服务层接口
 *
 */
public interface ISysDictService extends IService<SysDict> {

    public Map<String,Object> initDict();

    public Map<String,Object> initDictMap();

    void refresh();
}