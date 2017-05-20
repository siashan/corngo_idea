package com.corngo.wechat.handler;

import com.corngo.wechat.bean.message.WechatMsg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 文本消息
 * Created by kfc on 2015/7/15.
 */
@Component
public class TextHandler implements BaseHandler {
//    @Autowired
//    private ITrafficInfoService trafficInfoService;

    @Override
    public WechatMsg handleMsg(Map<String, String> params) {
        String content = params.get("Content");
        System.out.println("文本内容：" + content);
        /**
         * 1、根据消息内容给出响应的回复
         */
//        if ("1".equals(content)) {
//            TrafficInfo trafficInfo = trafficInfoService.selectLast();
//            if (null != trafficInfo) {
//                return new TextMsg().add("河南省交通运输厅高速公路路况信息：")
//                        .addln()
//                        .add(JoddDateUtil.date2String(trafficInfo.getCreateTime(), "YYYY年MM月DD日 hh点mm分"))
//                        .addln()
//                        .add(trafficInfo.getTextContent());
//            }
//            return new TextMsg("暂无路况信息");
//        }
        /**
         * 2、消息不处理
         */
        return null;
        /**
         * 3、返回固定的消息
         */
//        return new TextMsg("你好啊");
        /**
         * 4、多客服
         */
//        return new TransferCustomerServiceMsg();   // 多客服
    }
}
