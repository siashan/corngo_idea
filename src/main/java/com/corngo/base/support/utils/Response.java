package com.corngo.base.support.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kfc
 * @version 1.0 - 2016-11-25
 */
public class Response extends HashMap<String, Object> {

    public Response() {
        put("code", 200);
    }

    public static Response error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Response error(String msg) {
        return error(500, msg);
    }

    public static Response error(int code, String msg) {
        Response r = new Response();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Response ok(String msg) {
        Response r = new Response();
        r.put("msg", msg);
        return r;
    }

    public static Response ok(Map<String, Object> map) {
        Response r = new Response();
        r.putAll(map);
        return r;
    }

    public static Response ok() {
        return new Response();
    }

    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Response dt(Page page){
        Response r = new Response();
        r.remove("code");
        r.put("success", true);
        r.put("totalRows", page.getTotal());
        r.put("curPage", page.getCurrent());
        r.put("data", page.getRecords());
        return r;
    }

    public static Response lt(Object obj){
        Response r = new Response();
        r.put("data",obj);
        return r;
    }

    public static String redirect(String url) {
        return "redirect:" + url;
    }

    public static String to404(){
        return redirect("/system/404");
    }
}
