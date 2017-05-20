package com.corngo.base.support.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送工具类
 *
 * Created by kfc on 2017/5/15.
 */
public class MsgUtils {
    private static Logger logger = LoggerFactory.getLogger(MsgUtils.class);
    /**
     * 短信发送，单条
     * @param  params  参数
     * @param mobile  手机号
     * @param TemplateCode   模板code
     */
    public static boolean   sendSingleMsg(Map<String,String> params,String mobile,String TemplateCode) {
        logger.info("调用阿里云发送短信，手机号：{},发送内容{}",mobile,JSONObject.toJSONString(params));
        String host = "http://sms.market.alicloudapi.com";
        String path = "/singleSendSms";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE 77207ecf47fc4551aed47fe18b88aa7a");
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ParamString", JSONObject.toJSONString(params));
        querys.put("RecNum", mobile);
        querys.put("SignName","有象出行");
        querys.put("TemplateCode", TemplateCode);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String result = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(result);
            return jsonObject.getString("success").equals("true") ? true:false;
        } catch (Exception e) {
            logger.warn("短信发送异常");
            e.printStackTrace();
            return  false;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String,String> map = new HashMap<>();
        map.put("number", "韩寒");
        System.out.println(sendSingleMsg(map, "18736000723", "SMS_41670342"));
    }
}
