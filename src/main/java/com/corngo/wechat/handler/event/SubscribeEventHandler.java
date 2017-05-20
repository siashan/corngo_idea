package com.corngo.wechat.handler.event;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.corngo.base.support.spring.Configs;
import com.corngo.base.support.utils.IdUtils;

import com.corngo.wechat.base.WechatConstant;
import com.corngo.wechat.bean.message.TextMsg;
import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 关注事件
 * Created by kfc on 2015/7/15.
 * 特殊情况说明：
 * 1. 如果二维码为正常不带参数二维码名片推送消息里不含有 以下属性
 * <EventKey><![CDATA[qrscene_123123]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * 2. 如果二维码为活动推广二维码，公众号名片中含有其他参数 则含有上述两个参数
 * 在EventKey中qrscene_为前缀，后面为二维码的参数值
 * Ticket是二维码的ticket，可用来换取二维码图片
 */
@Component
public class SubscribeEventHandler implements EventHandler {


    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        String openID = params.get("FromUserName");
        System.out.println(openID);

        // 注册
//        EntityWrapper<Passenger> wrapper = new EntityWrapper<>();
//        wrapper.where("open_id = {0}", openID);
//        List<Passenger> passengers = passengerService.selectList(wrapper);
//        if (passengers.isEmpty() ||  passengers.size() < 1){
//            Passenger passenger = new Passenger();
//            passenger.setId(IdUtils.oracle(Passenger.class));
//            passenger.setOpenId(openID);
//            passengerService.insert(passenger);
//        }
        // 普通关注事件
        TextMsg msg = new TextMsg();
        msg.add("欢迎关注“有象出行”微信公众平台，点击此处https://open.weixin.qq.com/connect/oauth2/authorize?appid="+
                WechatConstant.APPID+
                "&redirect_uri=http%3A%2F%2F"+ Configs.getProperty("site")+
                "%2Fcorngo%2Fmobile%2Fpassport%2Fwechat&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
        msg.add("绑定账号");
        msg.addln();

        return msg;
    }
}

/**
 * http://mp.weixin.qq.com/wiki/7/9f89d962eba4c5924ed95b513ba69d9b.html
 * http://wxsyb.bama555.com/activity/wall?id=62378
 * http://wxq.poodo.com.cn/pc/home.do;JSESSIONID=df028277-1bdc-43bf-9f21-6a5a040f8154
 * http://www.oschina.net/code/snippet_89964_24868
 * http://sandbox.runjs.cn/show/9xk5mrc0
 */