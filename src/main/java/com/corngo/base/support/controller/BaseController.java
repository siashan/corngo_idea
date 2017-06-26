package com.corngo.base.support.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.corngo.admin.model.SysUser;
import com.corngo.base.support.spring.DateEditor;
import com.corngo.base.support.spring.StringEscapeEditor;
import com.corngo.base.support.utils.Shiro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 基础控制器
 *
 * @author kfc
 * @version 1.0 - 2016-10-14
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest req;


    @Autowired
    protected HttpSession session;

    protected boolean isGET() {
        return "GET".equalsIgnoreCase(req.getMethod());
    }

    protected String ctx() {
        return req.getContextPath();
    }



    /**
     * <p>
     * 获取分页对象
     * 每页20条
     * </p>
     */
    protected <T> Page<T> getPage() {
        return getPage(20);
    }

    /**
     * <p>
     * 获取分页对象
     * 自定义分页条数
     * </p>
     *
     * @param size 每页显示数量
     * @return
     */
    protected <T> Page<T> getPage(int size) {
        int pageSize = size, pageNo = 1;
        if (req.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        if (req.getParameter("curPage") != null) {
            pageNo = Integer.parseInt(req.getParameter("curPage"));
        }
        return new Page<T>(pageNo, pageSize);
    }

    protected String getRemoteIp() {
        String remoteIp = req.getHeader("x-forwarded-for");
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getHeader("X-Real-IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getHeader("Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getHeader("WL-Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getHeader("HTTP_CLIENT_IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getRemoteAddr();
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = req.getRemoteHost();
        }
        return remoteIp;
    }

    protected SysUser user(){
        return Shiro.getUser();
    }

    protected Long userid(){
        return Shiro.getUserId();
    }


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
}
