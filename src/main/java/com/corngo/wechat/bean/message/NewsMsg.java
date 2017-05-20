package com.corngo.wechat.bean.message;


import com.corngo.wechat.util.WechatMessageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息
 * Created by kfc on 2015-10-26.
 */
public class NewsMsg extends WechatMsg {
    private static final int WX_MAX_SIZE = 10;
    private              int maxSize     = WX_MAX_SIZE;
    private List<WechatArticle> WechatArticles;

    public NewsMsg() {
        this.WechatArticles = new ArrayList<WechatArticle>(maxSize);
    }

    public NewsMsg(int maxSize) {
        setMaxSize(maxSize);
        this.WechatArticles = new ArrayList<WechatArticle>(maxSize);
    }

    public NewsMsg(List<WechatArticle> WechatArticles) {
        setWechatArticles(WechatArticles);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        if (maxSize < WX_MAX_SIZE && maxSize >= 1) {
            this.maxSize = maxSize;
        }
        if (WechatArticles != null && WechatArticles.size() > this.maxSize) {
            WechatArticles = WechatArticles.subList(0, this.maxSize);
        }
    }

    public List<WechatArticle> getWechatArticles() {
        return WechatArticles;
    }

    public void setWechatArticles(List<WechatArticle> WechatArticles) {
        if (WechatArticles.size() > this.maxSize) {
            this.WechatArticles = WechatArticles.subList(0, this.maxSize);
        } else {
            this.WechatArticles = WechatArticles;
        }
    }

    public void add(String title) {
        add(title, null, null, null);
    }

    public void add(String title, String url) {
        add(title, null, null, url);
    }

    public void add(String title, String picUrl, String url) {
        add(new WechatArticle(title, null, picUrl, url));
    }

    public void add(String title, String description, String picUrl, String url) {
        add(new WechatArticle(title, description, picUrl, url));
    }

    public void add(WechatArticle WechatArticle) {
        if (this.WechatArticles.size() < maxSize) {
            this.WechatArticles.add(WechatArticle);
        }
    }

    @Override
    public String toString() {
        WechatMessageBuilder mb = new WechatMessageBuilder(super.toString());
        mb.addData("MsgType", "news");
        mb.addTag("WechatArticleCount", String.valueOf(WechatArticles.size()));
        mb.append("<WechatArticles>\n");
        for (WechatArticle WechatArticle : WechatArticles) {
            mb.append(WechatArticle.toXml());
        }
        mb.append("</WechatArticles>\n");
        mb.surroundWith("xml");
        return mb.toString();
    }
}
