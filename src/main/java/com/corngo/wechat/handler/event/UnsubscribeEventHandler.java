package com.corngo.wechat.handler.event;

import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 取消关注事件
 * Created by kfc on 2015/7/15.
 */
@Component
public class UnsubscribeEventHandler implements EventHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return null;
    }
}
