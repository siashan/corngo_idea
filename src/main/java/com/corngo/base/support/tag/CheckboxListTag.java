package com.corngo.base.support.tag;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import jodd.util.StringUtil;
import org.beetl.core.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 复选框
 * <#checkboxlist name ="" value="" list="" listKey="" listValue=""/>
 * name : 标签名称
 * value:标签值
 * list:列表
 * listKey:checkbox值
 * listValue:checkbox描述
 * Created by kfc on 2016/7/9.
 */
public class CheckboxListTag extends Tag {
    @Override
    public void render() {
        try {
            StringBuilder out = new StringBuilder();
            Map attrs = (Map) this.args[1];
            String name = (String) attrs.get("name");
            String value = (String) attrs.get("value");
            List list = (List) attrs.get("list");
            String listKey = (String) attrs.get("listKey");
            String listValue = (String) attrs.get("listValue");
            List<String> vals = Lists.newArrayList();
            if (StringUtil.isNotBlank(value)) {
                String[] values = value.split(",");
                vals = Arrays.asList(values);
            }
            JSONArray array = (JSONArray) JSONObject.toJSON(list);
            for (int i = 0; i < array.size(); i++) {
                out.append("<label style='margin-left:10px;'><input type='checkbox' ");
                JSONObject obj = (JSONObject) array.get(i);
                if (StringUtil.isNotBlank(name)) {
                    out.append("name='" + name + "'");
                }
                out.append("value='" + obj.get(listKey) + "'");
                if (vals != null && vals.contains(obj.get(listKey).toString())) {
                    out.append(" checked");
                }
                out.append(">"+obj.get(listValue));
                out.append("</label>");
            }
            this.ctx.byteWriter.writeString(out.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"1","2"};
        System.out.println(Arrays.asList(strs).contains("1"));
    }
}
