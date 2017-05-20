package com.corngo.base.support.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Created by kfc on 2015-10-25.
 */
public class ApplicationContextFixed extends XmlWebApplicationContext {
    @Override
    protected DefaultListableBeanFactory createBeanFactory() {
        DefaultListableBeanFactory beanFactory = super.createBeanFactory();
        beanFactory.setAllowRawInjectionDespiteWrapping(true);
        return beanFactory;
    }
}
