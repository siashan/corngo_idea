package com.corngo.generate;

import com.baomidou.mybatisplus.generator.config.*;

/**
 * Created by kfc on 2017/5/4.
 */
public class AbstractGeneratorFix {
    protected ConfigBuilderFix config;
    protected InjectionConfigFix injectionConfig;
    private DataSourceConfig dataSource;
    private StrategyConfig strategy;
    private PackageConfig packageInfo;
    private TemplateConfig template;
    private GlobalConfig globalConfig;

    public AbstractGeneratorFix() {
    }

    protected void initConfig(String output_dir_res,String resourceDir) {
        if(null == this.config) {
            this.config = new ConfigBuilderFix(this.packageInfo, this.dataSource, this.strategy, this.template, this.globalConfig,resourceDir,output_dir_res);
            if(null != this.injectionConfig) {
                this.injectionConfig.setConfig(this.config);
            }
        }

    }

    public DataSourceConfig getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
    }

    public StrategyConfig getStrategy() {
        return this.strategy;
    }

    public void setStrategy(StrategyConfig strategy) {
        this.strategy = strategy;
    }

    public PackageConfig getPackageInfo() {
        return this.packageInfo;
    }

    public void setPackageInfo(PackageConfig packageInfo) {
        this.packageInfo = packageInfo;
    }

    public TemplateConfig getTemplate() {
        return this.template;
    }

    public void setTemplate(TemplateConfig template) {
        this.template = template;
    }

    public ConfigBuilderFix getConfig() {
        return this.config;
    }

    public void setConfig(ConfigBuilderFix config) {
        this.config = config;
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public InjectionConfigFix getCfg() {
        return this.injectionConfig;
    }

    public void setCfg(InjectionConfigFix injectionConfig) {
        this.injectionConfig = injectionConfig;
    }
}
