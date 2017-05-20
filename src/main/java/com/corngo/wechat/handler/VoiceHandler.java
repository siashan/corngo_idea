package com.corngo.wechat.handler;

import com.corngo.wechat.bean.message.TransferCustomerServiceMsg;
import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 语音消息
 * Created by kfc on 2015/7/15.
 */
@Component
public class VoiceHandler implements BaseHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
//        String recognition = params.get("Recognition");
//        return new TextMsg("我听到了你说的："+recognition);
        return new TransferCustomerServiceMsg();
    }
}
