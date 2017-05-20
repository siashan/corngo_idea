package com.corngo.wechat.handler.event;

import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 模板消息推送完成事件
 * Created by kfc on 2015/7/31.
 */
@Component
public class TemplatesendjobfinishEventHandler implements EventHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return null;
    }
}
