package com.corngo.wechat.handler.event;

import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 点击菜单拉取消息时的事件推送
 * Created by kfc on 2015/7/15.
 */
@Component
public class ClickEventHandler implements EventHandler {


    @Override
    public WechatMsg handleMsg(Map<String, String> params) {

        return null;
    }
}
