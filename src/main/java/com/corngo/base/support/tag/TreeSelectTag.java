package com.corngo.base.support.tag;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jodd.util.StringUtil;
import org.beetl.core.Tag;

import java.util.List;
import java.util.Map;

/**
 * 下拉菜单
 * <p/>
 * <#select id="" name ="" value="" list = "" (非空) listKey = ""(非空) listValue=""(非空) headerKey = "" headerValue = "" (和headerKey成对出现)>
 * id:标签id
 * name:标签name
 * value:标签value值
 * list:数据列表  (不能为空)
 * listKey:列表数据源中元素对象的属性, 用于获取选项的值，对应于select标签中的value
 * listValue:表数据源中元素对象的属性, 用于获取选项的文本内容，对应于select标签中的option
 * headerKey:设置列表的题头主键值. 一定不能为空值!
 * headerValue:列表的题头选项值
 * Created by kfc on 2016/7/7.
 */
public class TreeSelectTag extends Tag {
    @Override
    public void render() {
        try {
            StringBuilder out = new StringBuilder();
            Map attrs = (Map) this.args[1];
            String id = (String) attrs.get("id");
            String name = (String) attrs.get("name");
            String value = attrs.get("value") + "";
            List list = (List) attrs.get("list");
            String listKey = (String) attrs.get("listKey");
            String listValue = (String) attrs.get("listValue");
            String headerKey = (String) attrs.get("headerKey");
            String headerValue = (String) attrs.get("headerValue");
            out.append("<select class='form-control m-bot15'");
            if (StringUtil.isNotBlank(id)) {
                out.append(" id = '" + id + "'");
            }
            if (StringUtil.isNotBlank(name)) {
                out.append(" name='" + name + "'");
            }
//
            out.append(">");
            if (null != headerKey && null != headerValue) {
                out.append("<option value='" + headerKey + "'>" + headerValue + "</option>");
            }
            JSONArray array = (JSONArray) JSONObject.toJSON(list);
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                if (StringUtil.isNotBlank(value) && value.equals(obj.getString(listKey))) {
                    out.append("<option value='" + obj.get(listKey) + "' selected>" + obj.get(listValue) + "</option>");
                } else {
                    out.append("<option value='" + obj.get(listKey) + "'>" + obj.get(listValue) + "</option>");
                }
            }
            out.append("</select>");
            this.ctx.byteWriter.writeString(out.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
