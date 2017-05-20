package com.corngo.wechat;

import com.alibaba.fastjson.JSONObject;
import com.corngo.wechat.base.WechatConstant;
import com.corngo.wechat.bean.button.Button;
import com.corngo.wechat.bean.button.SendBean;
import com.corngo.wechat.bean.button.SubButton;
import com.corngo.wechat.bean.message.TemplateMessage;
import com.corngo.wechat.util.WechatUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kfc on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring.xml",
        })
//@TransactionConfiguration(transactionManager = "activitiTransactionManager", defaultRollback = false)
@Transactional
public class WechatTestCase extends AbstractJUnit4SpringContextTests {

    @Autowired
    private WechatUtil wechatUtil;

    /**
     * 获取access_token
     */
    @Test
    public void testGetAccessToken() {
        // nyV1_Oe9CeXqfgVKpmdISRpK_Vh0cWI02e5zJJPV0hSYXDxphob-f4SGxD5VNLBWWcpA6XEvMo6fysiU_F2-nY9eqUQ4DwNv-SFmjf4jy2UUKCfACANDV
        String accessToken = wechatUtil.requestAccessToken();
        System.out.println(accessToken);
    }

    /**
     * 获取微信服务器地址
     */
    @Test
    public void testGetIp() {
        List<String> weichatIp = wechatUtil.getWeichatIp();
        for (String str : weichatIp) {
            System.out.println(str);
        }
    }

    @Test
    public void testCusMenu() throws UnsupportedEncodingException {
        // 第一个菜单
//        SubButton sb1_1 = new SubButton();
//        sb1_1.setName("我要出行");
//        sb1_1.setType("view");
//        sb1_1.setUrl("http://lwgs.hngslw.com/ZZCtrl/index/#/mapAll");
//        sb1_1.setUrl("http://1511w8t629.51mypc.cn/corngo/wechat/service/toTackTaxi");

//        SubButton sb1_2 = new SubButton();
//        sb1_2.setName("路况信息");
//        sb1_2.setType("view");
//        sb1_2.setUrl("http://1511w8t629.51mypc.cn/hngs/wechat/service/toPathStation");
//
//        SubButton sb1_3 = new SubButton();
//        sb1_3.setName("交通事件");
//        sb1_3.setType("view");
//        sb1_3.setUrl("http://1511w8t629.51mypc.cn/hngs/wechat/service/trafficEvent");
//
//        SubButton sb1_4 = new SubButton();
//        sb1_4.setName("站区分布");
//        sb1_4.setType("view");
//        sb1_4.setUrl("http://1511w8t629.51mypc.cn/hngs/wechat/service/zdfb");
//
//        SubButton sb1_5 = new SubButton();
//        sb1_5.setName("ETC网点");
//        sb1_5.setType("view");
//        sb1_5.setUrl("http://app1.9618968.com/appserver/api/servsite/index.html?from=groupmessage");
////
//        ArrayList<SubButton> asb1 = new ArrayList<SubButton>();
//        asb1.add(sb1_1);
//        asb1.add(sb1_2);
//        asb1.add(sb1_3);
//        asb1.add(sb1_4);
//        asb1.add(sb1_5);

        Button b1 = new Button();
        b1.setName("我要出行");
        b1.setType("view");
        b1.setUrl("http://1511w8t629.51mypc.cn/corngo/wechat/service/toTackTaxi");

        // 第二个菜单
        SubButton sb2_1 = new SubButton();
        sb2_1.setName("高速路况");
        sb2_1.setType("view");
        sb2_1.setUrl("http://zzairport.com/wap.php");

        SubButton sb2_2 = new SubButton();
        sb2_2.setName("APP下载");
        sb2_2.setType("view");
        sb2_2.setUrl("http://bdmap.weather.com.cn/mweather/101010100.shtml#1");

//        SubButton sb2_3 = new SubButton();
//        sb2_3.setName("最新消息");
//        sb2_3.setType("view");
//        sb2_3.setUrl("http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA5NzI4OTIxMA==&from=1#wechat_webview_type=1&wechat_redirect");
//
//        SubButton sb2_4 = new SubButton();
//        sb2_4.setName("邻省路况");
//        sb2_4.setType("view");
//        sb2_4.setUrl("http://henangstapi.u-road.com/HeNanGSWechatAPIServer/LinkServer/linkurl?from=singlemessage&isappinstalled=0&winzoom=1");

        ArrayList<SubButton> asb2 = new ArrayList<SubButton>();
        asb2.add(sb2_1);
        asb2.add(sb2_2);
//        asb2.add(sb2_3);
//        asb2.add(sb2_4);

        Button b2 = new Button();
        b2.setName("个人中心");
        b2.setSub_button(asb2);


        // 第三个菜单
//        SubButton sb3_1 = new SubButton();
//        sb3_1.setName("反馈意见");
//        sb3_1.setType("view");
//        sb3_1.setUrl("http://1511w8t629.51mypc.cn/hngs/wechat/service/toFeedBackAdd");
//
//        SubButton sb3_2 = new SubButton();
//        sb3_2.setName("使用说明");
//        sb3_2.setType("view");
//        sb3_2.setUrl("http://1511w8t629.51mypc.cn/hngs/wechat/service/usage");
//
//        ArrayList<SubButton> asb3 = new ArrayList<SubButton>();
//        asb3.add(sb3_2);
//        asb3.add(sb3_1);
//
//        Button b3 = new Button();
//        b3.setName("温馨互动");
//        b3.setSub_button(asb3);


        ArrayList<Button> sendList = new ArrayList<Button>();
        sendList.add(b1);
        sendList.add(b2);
//        sendList.add(b3);
        SendBean sendBean = new SendBean();
        sendBean.setButton(sendList);

        wechatUtil.setMenu(sendBean);

    }


    @Test
    public void testQueryMenu() {
        wechatUtil.queryMenu();
    }


    @Test
    public void testTemplateMsgSend() {
        TemplateMessage msg = TemplateMessage.initTemplateMessage("oeZ-WszgQXCQFOg6J4PTqOQxiYmc", WechatConstant.TEMPLATE_ID_1);
        msg.addDataFirst("你好", "#173177")
                .addData("keyword1", "韩寒")
                .addData("keyword2", "18703660612")
                .addData("keyword3", "2016年12月31日")
                .addData("keyword4", "测试一下啦")
                .addDataRemark();
        wechatUtil.push(msg);
    }


    @Test
    public void testAddTempFile() throws FileNotFoundException {
        File file = ResourceUtils.getFile("file:E:\\11.jpg");
        System.out.println(file.getName());
        wechatUtil.uploadTempFile(WechatConstant.MediaType.IMAGE, file);
    }

    @Test
    public void  testQueryMediaId() throws IOException {
        String media_id = "eQy_fnG8Kb_Sg4vURAScMWkOMmouUjsvbom5pBAUk2rG87AqpH7HIpMesn57VKSN";
        InputStream inputStream = wechatUtil.downLoadTempFile(media_id, false);
        File file = new File("D:\\11.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.copy(inputStream, fos);
    }
    @Test
    public void  testBasicInfo() throws IOException {
        String basicInfo = wechatUtil.userBasicInfo("oeZ-WszgQXCQFOg6J4PTqOQxiYmc");
        JSONObject retJson = JSONObject.parseObject(basicInfo);
        System.out.println(retJson.get("headimgurl"));
    }
}
