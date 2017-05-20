package com.corngo.wechat.bean.message;


import com.corngo.wechat.util.WechatMessageBuilder;

/**
 * 消息转发到多客服
 * Created by kfc on 2015-09-28.
 */
public class TransferCustomerServiceMsg extends WechatMsg {

    @Override
    public String toString() {
        WechatMessageBuilder mb = new WechatMessageBuilder(super.toString());
        mb.addData("MsgType", "transfer_customer_service");
        mb.surroundWith("xml");
        return mb.toString();
    }

}
