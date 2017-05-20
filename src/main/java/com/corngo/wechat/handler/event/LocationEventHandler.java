package com.corngo.wechat.handler.event;

import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 上报地理位置事件
 * 特殊说明：
            这里的地理位置上报事件跟消息不同：用户同意上报地理位置后，每次进入公众号会话时，
          都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平
          台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
 * Created by kfc on 2015/7/15.
 */
@Component
public class LocationEventHandler implements EventHandler {
    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        return null;
    }
}
