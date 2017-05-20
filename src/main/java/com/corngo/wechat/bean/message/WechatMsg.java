package com.corngo.wechat.bean.message;


import com.corngo.wechat.util.WechatMessageBuilder;

/**
 * Created by kfc on 2015/7/15.
 */
public class WechatMsg {
    private String toUserName;
    private String fromUserName;
    private long   createTime;
    private String msgType;

    public WechatMsg() {
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        WechatMessageBuilder builder = new WechatMessageBuilder(159);
        builder.addData("ToUserName", getToUserName());
        builder.addData("FromUserName", getFromUserName());
        builder.addTag("CreateTime", String.valueOf(System.currentTimeMillis()).substring(0, 10));
        return builder.toString();
    }
}
