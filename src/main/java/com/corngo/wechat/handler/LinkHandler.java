package com.corngo.wechat.handler;

import com.corngo.wechat.bean.message.TransferCustomerServiceMsg;
import com.corngo.wechat.bean.message.WechatMsg;

import java.util.Map;

/**
 * 链接消息
 * Created by kfc on 2015/7/15.
 */
public class LinkHandler implements BaseHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return new TransferCustomerServiceMsg();
    }
}
