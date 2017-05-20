package com.corngo.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.corngo.base.support.utils.HttpClientUtil;
import com.corngo.wechat.base.WechatConstant;
import com.corngo.wechat.bean.button.SendBean;
import com.corngo.wechat.bean.message.TemplateMessage;
import jodd.util.StringUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 微信工具类
 * <p/>
 * Created by kfc on 2017/1/6.
 */
@Component
public class WechatUtil {
    private Logger logger = LoggerFactory.getLogger(WechatUtil.class);
    // 微信AccessToken 缓存
    @Resource(name = "wechatCache")
    private Cache cache;

    private String accessToken = null;

    /**
     * 获取AccessToken，先从全局缓存中获取，获取不到再远程到微信服务器获取
     */
    public String requestAccessToken() {
        Element element = cache.get("accessToken");
        if (element == null || element.getObjectValue() == null || element.isExpired()) {
            logger.info("accessToken缓存无效或过期，重新获取");
            try {
                String result = HttpClientUtil.get(WechatConstant.TOKEN_GET_URL);
                logger.info("调用微信access_token接口返回结果：{}", result);
                JSONObject retJson = JSONObject.parseObject(result);
                if (retJson.containsKey("access_token")) {
                    accessToken = retJson.getString("access_token");
                    logger.info("调用微信接口获取access_token成功，access_token：{}", accessToken);
                    // 加入缓存
                    element = new Element("accessToken", accessToken);
                    cache.put(element);
                } else {
                    logger.warn("调用微信接口获取access_token失败，微信返回结果：result:{}", result);
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.warn("调用微信access_token接口时异常：" + e.getMessage());
                accessToken = requestAccessToken();
            }
        } else {
            accessToken = (String) element.getObjectValue();
        }
        logger.info("current access_token:{}", accessToken);
        return accessToken;
    }

    /**
     * 获取微信服务器ip地址
     *
     * @return
     */
    public List<String> getWeichatIp() {
        logger.info("获取微信服务器ip列表");
        String accessToken = requestAccessToken();
        List<String> ipArry = null;
        try {
            String result = HttpClientUtil.get(WechatConstant.WECHAT_IPLIST_PRE + accessToken);
            logger.info("调用微信获取服务器ip接口返回结果：{}", result);
            JSONObject retJson = JSONObject.parseObject(result);
            if (retJson.containsKey("ip_list")) {
                JSONArray ipList = retJson.getJSONArray("ip_list");
                ipArry = JSON.parseArray(ipList.toString(), String.class);
                logger.info("调用微信接口获取微信ip成功，ipList：{}", ipList.toString());
            } else {
                logger.warn("调用微信接口获取微信ip成功，微信返回结果：result:{}", result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("调用微信获取微信服务器ip接口时异常：" + e.getMessage());
            ipArry = getWeichatIp();
        }
        return ipArry;
    }

    /**
     * 设置自定义菜单
     */
    public void setMenu(SendBean sendBean) {
        logger.info("设置自定义菜单");
        String accessToken = requestAccessToken();
        try {
            JSONObject jsonStr = (JSONObject) JSON.toJSON(sendBean);
            String s = jsonStr.toJSONString();
            logger.info("菜单json串：{}", s);
            String result = HttpClientUtil.post(WechatConstant.CUS_MENU_SET_PRE + accessToken, s, null);
            logger.info("调用微信设置菜单接口返回结果：{}", result);
            JSONObject retJson = JSONObject.parseObject(result);
            if (retJson.getString("errcode").equals("0")) {
                logger.info("菜单设置成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("调用微信设置菜单接口时异常：" + e.getMessage());
        }
    }

    /**
     * 查询自定义菜单结构
     *
     * @return
     */
    public String queryMenu() {
        logger.info("查询自定义菜单");
        String menu = null;
        String accessToken = requestAccessToken();
        try {
            String result = HttpClientUtil.get(WechatConstant.CUS_MENU_QUERY_PRE + accessToken);
            logger.info("调用微信查询菜单接口返回结果：{}", result);
            menu = result;
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("调用微信设置菜单接口时异常：" + e.getMessage());
        }
        return menu;
    }

    /**
     * 模板消息推送
     *
     * @param message
     * @return
     */
    public String push(TemplateMessage message) {
        logger.info("发送模板消息开始！");
        String accessToken = requestAccessToken();
        String sendStr = JSONObject.toJSONString(message);
        logger.info("模板消息内容：{}", sendStr);
        try {
            String result = HttpClientUtil.post(WechatConstant.TEMPLATE_MSG_SEND_PRE + accessToken, sendStr, null);
            logger.info("模板消息发送结果:{}", result);
            return result;
        } catch (IOException e) {
            logger.warn("模板消息发送异常");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传临时素材
     *
     * @param type  类型；分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息
     * @return   返回media_id  如果上传成功返回，不成功返回null
     */
    public String uploadTempFile(String type, File media){
        logger.info("上传临时素材开始");
        String accessToken = requestAccessToken(); // access_token
        String postUrl = WechatConstant.ADD_TEMP_FILE_PRE;
        MultipartEntityBuilder mBuilder = get_COMPATIBLE_Builder("UTF-8");// 获得utf-8编码的mbuilder
        mBuilder.addBinaryBody("media", media, ContentType.APPLICATION_OCTET_STREAM, media.getName());// 这里就是我要上传到服务器的多媒体图片
        HttpPost post = new HttpPost(postUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(media);
        builder.addPart("media", fileBody);
        builder.addPart("access_token", new StringBody(accessToken, ContentType.TEXT_PLAIN));
        builder.addPart("type", new StringBody(type, ContentType.TEXT_PLAIN));
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        String result = HttpClientUtil.postHttps(post);
        logger.info("上传临时图片返回结果：{}",result);

        if (StringUtil.isBlank(result)){
            logger.warn("上传临时素材发生异常！");
        }else{
            JSONObject retJson = JSONObject.parseObject(result);
            if (retJson.containsKey("media_id")) {
                logger.info("临时素材上传成功！");
                return retJson.getString("media_id");
            }
        }
        return null;
    }

    /**
     * 下载临时素材
     *
     * @param mediaId  素材media_id
     * @param isVideo 是否是视频文件（视频文件不支持HTTPS）
     * @return  返回文件流，为null时说明获取文件失败
     */
    public InputStream downLoadTempFile(String mediaId, boolean isVideo){
        logger.info("下载临时素材开始");
//        String accessToken = requestAccessToken(); // access_token
        String accessToken = "dq9fGLKR2WZ4SgmUS68vsD0lBzU2a5g4k2KSEMccXDO-N3uupTzC5jV3VlgXRhG5i4G6kqEQiqnSQyUB0sdDgTlcWTDWoweVZ1J3Zz8qd4kbN_2mGpbpTMQhYSQVbRmDBDUgABATVM"; // access_token
        String requestUrl = isVideo?WechatConstant.GET_TEMP_FILE_PRE_VIDEO:WechatConstant.GET_TEMP_FILE_PRE;
        requestUrl = requestUrl.replace("${ACCESS_TOKEN}",accessToken).replace("${MEDIA_ID}",mediaId);
        System.out.println(requestUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        try {
            return HttpClientUtil.request(httpClient, httpGet);
        } catch (IOException e) {
            logger.info("下载临时素材异常");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户openID获取用户基本信息
     * @param openId  用户open_id
     * @return  返回微信返回的json字符串
     */
    public String userBasicInfo(String openId){
        logger.info("获取用户基本信息开始");
        String accessToken = requestAccessToken();
        String url = WechatConstant.USER_BASIC_INFO.replace("${ACCESS_TOKEN}", accessToken).replace("${OPENID}", openId);
        try {
            String result = HttpClientUtil.get(url);
            logger.info("获取用户基本信息返回结果:{}",result);
           return result;
        } catch (IOException e) {
            logger.info("获取用户基本信息异常！！");
            e.printStackTrace();
        }
        return null;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static MultipartEntityBuilder get_COMPATIBLE_Builder(String charSet) {
        MultipartEntityBuilder result = MultipartEntityBuilder.create();
        result.setBoundary(HttpClientUtil.getBoundary())
                .setCharset(Charset.forName(charSet))
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        return result;
    }

    public static void main(String[] args) {
        String str = "{\"ip_list\":[\"101.226.62.77\",\"101.226.62.78\",\"101.226.62.79\",\"101.226.62.80\",\"101.226.62.81\",\"101.226.62.82\",\"101.226.62.83\",\"101.226.62.84\",\"101.226.62.85\",\"101.226.62.86\",\"101.226.103.59\",\"101.226.103.60\",\"101.226.103.61\",\"101.226.103.62\",\"101.226.103.63\",\"101.226.103.69\",\"101.226.103.70\",\"101.226.103.71\",\"101.226.103.72\",\"101.226.103.73\",\"140.207.54.73\",\"140.207.54.74\",\"140.207.54.75\",\"140.207.54.76\",\"140.207.54.77\",\"140.207.54.78\",\"140.207.54.79\",\"140.207.54.80\",\"182.254.11.203\",\"182.254.11.202\",\"182.254.11.201\",\"182.254.11.200\",\"182.254.11.199\",\"182.254.11.198\",\"59.37.97.100\",\"59.37.97.101\",\"59.37.97.102\",\"59.37.97.103\",\"59.37.97.104\",\"59.37.97.105\",\"59.37.97.106\",\"59.37.97.107\",\"59.37.97.108\",\"59.37.97.109\",\"59.37.97.110\",\"59.37.97.111\",\"59.37.97.112\",\"59.37.97.113\",\"59.37.97.114\",\"59.37.97.115\",\"59.37.97.116\",\"59.37.97.117\",\"59.37.97.118\",\"112.90.78.158\",\"112.90.78.159\",\"112.90.78.160\",\"112.90.78.161\",\"112.90.78.162\",\"112.90.78.163\",\"112.90.78.164\",\"112.90.78.165\",\"112.90.78.166\",\"112.90.78.167\",\"140.207.54.19\",\"140.207.54.76\",\"140.207.54.77\",\"140.207.54.78\",\"140.207.54.79\",\"140.207.54.80\",\"180.163.15.149\",\"180.163.15.151\",\"180.163.15.152\",\"180.163.15.153\",\"180.163.15.154\",\"180.163.15.155\",\"180.163.15.156\",\"180.163.15.157\",\"180.163.15.158\",\"180.163.15.159\",\"180.163.15.160\",\"180.163.15.161\",\"180.163.15.162\",\"180.163.15.163\",\"180.163.15.164\",\"180.163.15.165\",\"180.163.15.166\",\"180.163.15.167\",\"180.163.15.168\",\"180.163.15.169\",\"180.163.15.170\",\"101.226.103.0\\/25\",\"101.226.233.128\\/25\",\"58.247.206.128\\/25\",\"182.254.86.128\\/25\",\"103.7.30.21\",\"103.7.30.64\\/26\",\"58.251.80.32\\/27\",\"183.3.234.32\\/27\",\"121.51.130.64\\/27\"]}\n";
        JSONObject parse = (JSONObject) JSONObject.parse(str);
        JSONArray ip_list = (JSONArray) parse.get("ip_list");
        System.out.println(ip_list.toString());
        ip_list.toArray();
        List<String> list = JSON.parseArray(ip_list.toString(), String.class);

        for (String strr : list) {
            System.out.println(strr);
        }
    }
}
