package com.corngo.wechat.bean.message;

import com.alibaba.fastjson.JSONObject;
import com.corngo.wechat.base.WechatConstant;
import jodd.datetime.JDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 推送模板消息
 * Created by kfc on 2015/7/15.
 */
public class TemplateMessage {
    private String touser;     // 微信open_id
    private String template_id;  // 模板消息id
    private String url;
    private String topcolor;
    private JSONObject data;


    //首页链接
    public static final String FIRST = "尊敬的";
    // remark
    public static final String REMARK = "\n测试账号不要见怪";


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getTIME() {
        return new JDateTime().toString(WechatConstant.DEFAULT_DATE_FORMAT_JODD);
    }

    public static TemplateMessage initTemplateMessage(String openId, String tmp_id) {
        TemplateMessage msg = new TemplateMessage();
//        msg.setUrl(URL_INDEX);
        msg.setTouser(openId);
        msg.setTemplate_id(tmp_id);
        msg.setData(new JSONObject());
        return msg;
    }

    public TemplateMessage addData(String key, String value) {
        JSONObject tmp = new JSONObject();
        tmp.put("value", value);
        data.put(key, tmp);
        return this;
    }

    public TemplateMessage addData(String key, String value, String color) {
        JSONObject tmp = new JSONObject();
        tmp.put("value", value);
        tmp.put("color", color);
        data.put(key, tmp);
        return this;
    }

    public TemplateMessage addDataFirst(String username, boolean init) {
        if (init) {
            addData("first", FIRST + " " + username + " ：");
        } else {
            addData("first", username);
        }

        return this;
    }

    public TemplateMessage addDataFirst(String value, String color) {
        addData("first", value, color);
        return this;
    }

    public TemplateMessage addDataRemark() {
        addData("remark", REMARK);
        return this;
    }

    public TemplateMessage addDataRemark(String value) {
        addData("remark", value);
        return this;
    }

    public TemplateMessage addDataRemark(String value, String color) {
        addData("remark", value, color);
        return this;
    }

    public TemplateMessage addDataDate(String key) {
        addData(key, getTIME());
        return this;
    }

    public TemplateMessage addDataDate(String key, String color) {
        addData(key, getTIME(), color);
        return this;
    }

    public TemplateMessage addDataDate(String key, Date date) {
        addData(key, sdf.format(date));
        return this;
    }

    public TemplateMessage addDataDate(String key, Date date, String color) {
        addData(key, sdf.format(date), color);
        return this;
    }


    public String getTouser() {
        return touser;
    }

    public TemplateMessage setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public TemplateMessage setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TemplateMessage setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public TemplateMessage setTopcolor(String topcolor) {
        this.topcolor = topcolor;
        return this;
    }

    public JSONObject getData() {
        return data;
    }

    public TemplateMessage setData(JSONObject data) {
        this.data = data;
        return this;
    }


}
