package com.corngo.base.support.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * Created by kfc on 2015-10-22.
 */
public class SpringLoaderListener extends ContextLoaderListener {

    private static Logger log = LoggerFactory.getLogger(SpringLoaderListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("Spring Loader Listener is starting...");
        super.contextInitialized(event);
    }
}
