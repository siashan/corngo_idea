package com.corngo.base.support.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by kfc on 2016/11/14.
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        SpringContextHolder.ctx = ctx;
    }

    public static Object getBean(String beanName){
        return ctx.getBean(beanName);
    }

    public static <T> T getBean(Class<T> tClass) {
        return ctx.getBean(tClass);
    }
}
