package com.corngo.base.support.utils;

import com.corngo.base.support.PUBConstants;
import com.corngo.base.support.orm.Page;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * HttpServletRequest 请求封装工具类
 * Created by kfc on 2016/6/29.
 */
public class Requests {
    /**
     * BSGrid分页参数自动封装
     *
     * @param request
     * @return
     */
    public static Page initPage(HttpServletRequest request) {
        String cur = request.getParameter("curPage");
        String size = request.getParameter("pageSize");
        int curPage = (cur == null ? 1 : Integer.parseInt(cur));
        int pageSize = (size == null ? PUBConstants.DEFAULT_PAGESIZE : Integer.parseInt(size));
        return new Page(curPage, pageSize);
    }

    /**
     * 获取远程访问IP
     *
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String remoteIp = null;
        if (remoteIp == null || remoteIp.length() == 0) {
            remoteIp = request.getHeader("x-forwarded-for");
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader("X-Real-IP");
            }
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader("Proxy-Client-IP");
            }
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader("WL-Proxy-Client-IP");
            }
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader("HTTP_CLIENT_IP");
            }
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getRemoteAddr();
            }
            if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getRemoteHost();
            }
        }
        return remoteIp;
    }

//    public static void setActionFlag(Model model, ActionFlag flag) {
//        model.addAttribute(PUBConstants.ACTION_FLAG, flag.value());
//    }

    /**
     * 封装请求参数到map
     *
     * @param request
     * @return
     */
    public static Map<String, String> requestMap(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, request.getParameter(key));
        }
        return map;
    }
}
