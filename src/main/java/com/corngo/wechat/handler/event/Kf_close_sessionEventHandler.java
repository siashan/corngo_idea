package com.corngo.wechat.handler.event;

import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 多客服会话关闭事件
 * Created by kfc on 2015-09-29.
 */
@Component
public class Kf_close_sessionEventHandler implements EventHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return null;
    }
}
