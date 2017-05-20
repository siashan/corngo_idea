package com.corngo.generate;

import com.baomidou.mybatisplus.generator.config.FileOutConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by kfc on 2017/5/4.
 */
public abstract class InjectionConfigFix {
    private ConfigBuilderFix config;
    private Map<String, Object> map;
    private List<FileOutConfig> fileOutConfigList;

    public InjectionConfigFix() {
    }

    public abstract void initMap();

    public ConfigBuilderFix getConfig() {
        return this.config;
    }

    public void setConfig(ConfigBuilderFix config) {
        this.config = config;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<FileOutConfig> getFileOutConfigList() {
        return this.fileOutConfigList;
    }

    public void setFileOutConfigList(List<FileOutConfig> fileOutConfigList) {
        this.fileOutConfigList = fileOutConfigList;
    }
}
