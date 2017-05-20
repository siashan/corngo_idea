package com.corngo.wechat.bean.message;


import com.corngo.wechat.util.WechatMessageBuilder;

/**
 * 文本消息
 * Created by kfc on 2015/7/15.
 */
public class TextMsg extends WechatMsg {
    private StringBuilder contentBuilder;

    public TextMsg() {
        contentBuilder = new StringBuilder();
    }

    public TextMsg(String content) {
        setContent(content);
    }

    public String getContent() {
        return contentBuilder.toString();
    }

    public void setContent(String content) {
        contentBuilder = new StringBuilder(content);
    }

    public TextMsg add(String text) {
        contentBuilder.append(text);
        return this;
    }

    public TextMsg addln() {
        return add("\n");
    }

    public TextMsg addln(String text) {
        contentBuilder.append(text);
        return addln();
    }

    public TextMsg addLink(String text, String url) {
        contentBuilder.append("<a href=\"").append(url).append("\">")
                .append(text).append("</a>");
        return this;
    }

    @Override
    public String toString() {
        WechatMessageBuilder mb = new WechatMessageBuilder(super.toString());
        mb.addData("Content", contentBuilder.toString().trim());
        mb.addData("MsgType", "text");
        mb.surroundWith("xml");
        return mb.toString();
    }
}
