package com.corngo.wechat.base;

import java.io.UnsupportedEncodingException;

/**
 * 微信配置类
 * <p/>
 * Created by kfc on 2017/1/6.
 */
public class WechatConstant {
    // 第三方用户唯一凭证
    public static final String APPID = "wxc1157fa858901f6d";
    // 第三方用户唯一凭证密钥，即appsecret
    public static final String APPSECRET = "24a79d2c4b5fd7a319aaa88288d827a5";
    // token
    public static final String TOKEN = "han123";
    // 时间格式
    public static final String DEFAULT_DATE_FORMAT_JODD = "YYYY-MM-DD hh:mm:ss";

    // 请求access_token的url地址(GET)
    public static String TOKEN_GET_URL = null;

    // 获取微信服务器ip地址前缀（GET）
    public static String WECHAT_IPLIST_PRE = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=";

    // 自定义菜单前缀(POST)
    public static String CUS_MENU_SET_PRE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    // 自定义菜单查询接口（GET）
    public static String CUS_MENU_QUERY_PRE = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";

    // 发送模板消息(POST)
    public static String TEMPLATE_MSG_SEND_PRE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    // 新增临时素材(POST)
    public static String ADD_TEMP_FILE_PRE = "https://api.weixin.qq.com/cgi-bin/media/upload";

    // 获取临时素材（GET）
    public static String GET_TEMP_FILE_PRE = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=${ACCESS_TOKEN}&media_id=${MEDIA_ID}";
    // 获取临时素材（GET）--视频素材 不支持https
    public static String GET_TEMP_FILE_PRE_VIDEO = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=${ACCESS_TOKEN}&media_id=${MEDIA_ID}";

    // 获取用户基本信息（GET）
    public static String USER_BASIC_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=${ACCESS_TOKEN}&openid=${OPENID}&lang=zh_CN";
    // 模板id
    public static final String TEMPLATE_ID_1 = "_nNq23DBCnIUVyc_DQJvkzAju7rbzwgthPo0Uysm2Zw";


    static {
        TOKEN_GET_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
    }

    /**
     * 临时素材类型
     * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     */
    public static class MediaType{
        // 图片
        public static String IMAGE = "image";
        // 语音
        public static String VOICE = "voice";
        // 视频
        public static String VIDEO = "video";
        // 缩略图
        public static String THUMB = "thumb";

    }


    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(URLEncoder.encode("http://1511w8t629.51mypc.cn/webtemp","UTF-8"));

        System.out.println(GET_TEMP_FILE_PRE.replace("${ACCESS_TOKEN}","333").replace("${MEDIA_ID}","111"));
    }

}
