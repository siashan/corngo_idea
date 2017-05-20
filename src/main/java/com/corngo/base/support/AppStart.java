package com.corngo.base.support;

import com.corngo.admin.service.ISysDictService;
import com.corngo.base.support.spring.Configs;
import com.corngo.base.support.tag.ButtonTag;
import com.corngo.base.support.tag.CheckboxListTag;
import com.corngo.base.support.tag.DictRadioTag;
import com.corngo.base.support.tag.SelectTag;
import com.google.common.collect.Maps;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * 项目启动
 * 初始化各项配置和缓存
 * Created by kfc on 2016/6/27.
 */
public class AppStart {
    private static Logger log = LoggerFactory.getLogger(AppStart.class);
    private BeetlGroupUtilConfiguration beetlConfig;
    private ISysDictService sysDictServiceImpl;

    public void onStart() throws IOException {
        log.info("平台正在启动");
//        配置你的版权
        log.info("版权所有：Copyright ? 2016 website.com");
        log.info("读取配置文件到缓存");
        String runtime = Configs.getProperty("runtime");
        log.info("加载数据字典到缓存");
        sysDictServiceImpl.initDict();
        sysDictServiceImpl.initDictMap();
        log.info("模板引擎全局变量定义");
        // 注册自定义标签
        GroupTemplate group = beetlConfig.getGroupTemplate();
        group.registerTag("button", ButtonTag.class);
        group.registerTag("dictRadio", DictRadioTag.class);
        group.registerTag("select", SelectTag.class);
        group.registerTag("checkboxlist", CheckboxListTag.class);
        Map<String, Object> shared = Maps.newHashMap();
        shared.put("runtime", runtime.equalsIgnoreCase("dev") ? 0 : 1);     // 判断开发模式、生产模式
        shared.put("fileService", Configs.getProperty("fileservice"));
        shared.put("previewService", Configs.getProperty("preview"));
//        shared.put("respath", Configs.getProperty("respath"));
        group.setSharedVars(shared);
    }

    public void onStop() {
        log.info("平台正在关闭");
    }
    public void setBeetlConfig(BeetlGroupUtilConfiguration beetlConfig) {
        this.beetlConfig = beetlConfig;
    }
    public BeetlGroupUtilConfiguration getBeetlConfig() {
        return beetlConfig;
    }

    public ISysDictService getSysDictServiceImpl() {
        return sysDictServiceImpl;
    }

    public void setSysDictServiceImpl(ISysDictService sysDictServiceImpl) {
        this.sysDictServiceImpl = sysDictServiceImpl;
    }
}
