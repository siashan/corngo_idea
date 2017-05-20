package com.corngo.wechat.controller;

import com.alibaba.druid.util.HexBin;
import com.corngo.base.support.spring.SpringContextHolder;
import com.corngo.base.support.utils.Requests;
import com.corngo.base.support.utils.security.Digests;
import com.corngo.wechat.base.WechatConstant;
import com.corngo.wechat.bean.message.WechatMsg;
import com.corngo.wechat.handler.BaseHandler;
import com.corngo.wechat.handler.event.EventHandler;
import com.corngo.wechat.util.WechatMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 *微信消息
 *
 * Created by kfc on 2017/1/6.
 */
@Controller
@RequestMapping("wechat")
public class WechatController {
    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    public static String appId;
    public static String secret;
    public static String token;

    static {
        token = WechatConstant.TOKEN;
        appId = WechatConstant.APPID;
        secret =WechatConstant.APPSECRET;
    }
    /**
     * 微信发起绑定服务器的Get请求
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "wechat", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("微信服务器绑定开始");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if (checkSignature(signature, timestamp, nonce)) {
            logger.info("微信服务器绑定成功");
            out.print(echostr);
        } else {
            logger.warn("微信服务器绑定失败");
        }
        out.close();
    }

    /**
     * 接收用户微信消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "wechat", method = RequestMethod.POST)
    @ResponseBody
    public String post(HttpServletRequest request, HttpServletResponse response) {
        logger.info("接收用户微信消息 :  {}", Requests.requestMap(request));
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if (!checkSignature(signature, timestamp, nonce)) {
            logger.warn("微信消息签名验证失败");
            return "";
        }
        String result = processRequest(request, response);
        try {
            response.setContentType("text/xml;charset=UTF-8");
            logger.info("回复：{}", result);
            response.getWriter().write(result);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }





    private String processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> params = WechatMessageBuilder.parse(request);
            logger.info("解析参数：{}", params);
            String fromUserName = params.get("FromUserName");
            String toUserName = params.get("ToUserName");
            String msgType = params.get("MsgType");
            Object ret = null;
            WechatMsg msg = null;
            if (msgType.equals("event")) {
                String event = params.get("Event");
                logger.info("处理{}事件", event);
                EventHandler hander = (EventHandler) SpringContextHolder.getBean(event.toLowerCase() + "EventHandler");
                msg = hander.handleMsg(params);
            } else {
                logger.info("处理{}消息", msgType);
                BaseHandler handler = (BaseHandler) SpringContextHolder.getBean(msgType.toLowerCase() + "Handler");
                msg = handler.handleMsg(params);
            }
            if (null != msg) {
                msg.setFromUserName(toUserName);
                msg.setToUserName(fromUserName);
                // TODO 交互消息暂时不做加密
                return msg.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = HexBin.encode(Digests.sha1(content.toString().getBytes()));
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }
}
