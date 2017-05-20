package com.corngo.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 微信消息构造器
 * Created by kfc on 2015/5/25.
 */
public class WechatMessageBuilder {

    /**
     * 解析收到的微信消息
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parse(HttpServletRequest request) throws Exception {
        Map<String,String> ret = Maps.newHashMap();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList){
            ret.put(e.getName(), e.getText());
        }
        inputStream.close();
        return ret;
    }



    /**
     * 客服消息推送
     * @param message
     * @param accessToken
     * @return
     */
    public static String kfPush(JSONObject message, String accessToken){
        StringEntity entity = new StringEntity(message.toJSONString(),"utf-8");
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        HttpUriRequest req = RequestBuilder.post(url)
                .setEntity(entity)
                .build();
        try {
            CloseableHttpResponse resp = HttpClients.createDefault().execute(req);
            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity respEntity = resp.getEntity();
                String content = EntityUtils.toString(respEntity);
                JSONObject jsonObject = JSONObject.parseObject(content);
                return jsonObject.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private StringBuilder builder;

    public WechatMessageBuilder() {
        builder = new StringBuilder();
    }

    public WechatMessageBuilder(int capacity) {
        builder = new StringBuilder(capacity);
    }

    public WechatMessageBuilder(String str) {
        builder = new StringBuilder(str);
    }

    public void append(String str) {
        builder.append(str);
    }

    public void insert(String str) {
        builder.insert(0, str);
    }

    public void surroundWith(String tag) {
        StringBuilder sb = new StringBuilder(builder.capacity() + tag.length()
                * 2 + 5);
        sb.append("<").append(tag).append(">\n").append(builder).append("</")
                .append(tag).append(">\n");
        builder = sb;
    }

    public void addTag(String tagName, String text) {
        if (text == null) {
            return;
        }
        builder.append("<").append(tagName).append(">").append(text)
                .append("</").append(tagName).append(">\n");
    }

    public void addData(String tagName, String data) {
        if (data == null) {
            return;
        }
        builder.append("<").append(tagName).append("><![CDATA[").append(data)
                .append("]]></").append(tagName).append(">\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }

}
