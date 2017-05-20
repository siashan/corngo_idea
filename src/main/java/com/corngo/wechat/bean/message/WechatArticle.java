package com.corngo.wechat.bean.message;


import com.corngo.wechat.util.WechatMessageBuilder;

/**
 * Created by kfc on 2015-10-26.
 */
public class WechatArticle {
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public WechatArticle() {

    }

    public WechatArticle(String title) {
        this.title = title;
    }

    public WechatArticle(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public WechatArticle(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toXml() {
        WechatMessageBuilder mb = new WechatMessageBuilder();
        mb.addData("Title", title);
        mb.addData("Description", description);
        mb.addData("PicUrl", picUrl);
        mb.addData("Url", url);
        mb.surroundWith("item");
        return mb.toString();
    }
}
