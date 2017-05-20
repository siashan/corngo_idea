package com.corngo.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.corngo.base.support.cache.SimpleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corngo.admin.mapper.SysDictMapper;
import com.corngo.admin.model.SysDict;
import com.corngo.admin.service.ISysDictService;

import java.util.List;
import java.util.Map;

/**
 * SysDict 表数据服务层接口实现类
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {
    private Logger logger = LoggerFactory.getLogger(SysDictServiceImpl.class);

    @Autowired
    private SimpleCache cacheService;

    /**
     * 初始化数据字典
     *
     * @return
     */
    @Override
    public Map<String, Object> initDict() {
        if (cacheService.has("dict")) {
            return (Map<String, Object>) cacheService.get("dict");
        }
        logger.info("加载数据字典并缓存");
        Map<String, Object> dicts = Maps.newHashMap();
        EntityWrapper<SysDict> wrapper = new EntityWrapper<SysDict>();
        List<SysDict> allDict = selectList(wrapper);
        for (SysDict dic : allDict) {
            Object obj = dicts.get(dic.getDictGroup());
            if (obj == null) {
                List<SysDict> temp = Lists.newArrayList();
                temp.add(dic);
                dicts.put(dic.getDictGroup(), temp);
            } else {
                List<SysDict> temp = (List<SysDict>) obj;
                temp.add(dic);
            }
        }
        cacheService.put("dict", dicts);
        return dicts;
    }

    /**
     * 初始化数据字典，转换成map
     *
     * @return
     */
    public Map<String, Object> initDictMap() {
        if (cacheService.has("dictMap")) {
            return (Map<String, Object>) cacheService.get("dictMap");
        }
        Map<String, Object> dicts = Maps.newHashMap();
        EntityWrapper<SysDict> wrapper = new EntityWrapper<SysDict>();
        List<SysDict> allDict = selectList(wrapper);
        for (SysDict dic : allDict) {
            Object obj = dicts.get(dic.getDictGroup());
            if (obj == null) {
                Map<String, String> temp = Maps.newHashMap();
                temp.put("d_" + dic.getDictCode(), dic.getDictName());
                dicts.put(dic.getDictGroup(), temp);
            } else {
                Map<String, String> temp = (Map<String, String>) obj;
                temp.put("d_" + dic.getDictCode(), dic.getDictName());
            }
        }
        cacheService.put("dictMap", dicts);
        return dicts;
    }


    /**
     * 刷新缓存
     */
    public void refresh() {
        logger.info("刷新数据字典缓存");
        cacheService.remove("dict");
        cacheService.remove("dictMap");
        initDict();
        initDictMap();
    }
}