package com.corngo.base.support.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 继承spring PropertyPlaceholderConfigurer类
 * 在该类中添加自己的业务逻辑
 */
public class Configs extends PropertyPlaceholderConfigurer {

    private static Logger log = LoggerFactory.getLogger(Configs.class);

    // 所有配置项
    private static Properties prop;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        log.info("加载配置文件");
        Configs.prop = props;
        super.processProperties(beanFactoryToProcess, props);
    }

    public static String getProperty(String key) {
        if (null != prop && prop.containsKey(key)) {
            return prop.getProperty(key);
        }
        return null;
    }
}