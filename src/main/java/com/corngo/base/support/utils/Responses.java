package com.corngo.base.support.utils;

import com.corngo.base.support.PUBConstants;
import com.google.common.collect.Maps;
import jodd.util.StringUtil;

import java.util.Map;

/**
 * AJAX响应工具类
 * Created by kfc on 2014/12/19.
 */
public class Responses {

    public static Map<String, Object> success(Object data) {
        return msg(PUBConstants.SUCCESS_CODE, data);
    }

    public static Map<String, Object> success() {
        return success(null);
    }

    public static Map<String, Object> error(Object errorMsg) {
        return msg(PUBConstants.ERROR_CODE, errorMsg);
    }

    public static Map<String, Object> msg(String code, Object data) {
        Map<String, Object> ret = Maps.newHashMap();
        ret.put("code", code);
        if (data != null) {
            ret.put("data", data);
        } else {
            ret.put("data", "");
        }
        return ret;
    }

    public static Map<String, Object> grid(boolean success, int totalRows, int curPage, Object data) {
        Map<String, Object> ret = Maps.newHashMap();
        ret.put("success", success);
        ret.put("totalRows", totalRows);
        ret.put("curPage", curPage);
        ret.put("data", data);
        return ret;
    }

    public static Map<String, Object> valid(boolean success, String msg) {
        Map<String, Object> ret = Maps.newHashMap();
        if (success) {
            ret.put("ok", " ");
        } else {
            ret.put("error", StringUtil.toSafeString(msg));
        }
        return ret;
    }

//    public static Map<String, Object> um(boolean success, String name, String url, UploadState state) {
//        Map<String, Object> ret = Maps.newHashMap();
//        if (success) {
//            ret.put("name",name);
//            ret.put("url",url);
//        }
//        ret.put("state", state.value());
//        return ret;
//    }

}
