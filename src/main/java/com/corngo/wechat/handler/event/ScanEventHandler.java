package com.corngo.wechat.handler.event;

import com.corngo.wechat.bean.message.TextMsg;
import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户已关注时的事件推送
 * 特殊说明：
 * 这种情况只发生在已经关注的用户参加扫码活动时
 * Created by kfc on 2015/7/15.
 */
@Component
public class ScanEventHandler implements EventHandler {

    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return work(params);
    }

    public static WechatMsg work(Map<String, String> params){
        // 金钱豆征名活动
        TextMsg msg = new TextMsg();
        msg.add("一字千金，请你出名");
        msg.addln();
        return msg;
    }
}
