package com.corngo.wechat.handler;


import com.corngo.wechat.bean.message.WechatMsg;

import java.util.Map;

/**
 * 微信消息处理接口
 * Created by kfc on 2015/7/14.
 */
public interface BaseHandler {
    public WechatMsg handleMsg(Map<String, String> params);
}
