package com.corngo.wechat.handler;

import com.corngo.wechat.bean.message.TransferCustomerServiceMsg;
import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 视频消息
 * Created by kfc on 2015/7/15.
 */
@Component
public class VideoHandler implements BaseHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return new TransferCustomerServiceMsg();
    }
}
