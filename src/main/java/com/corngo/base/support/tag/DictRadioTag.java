package com.corngo.base.support.tag;

import com.corngo.admin.model.SysDict;
import org.beetl.core.Tag;

import java.util.List;
import java.util.Map;

/**
 * 根据数据字典生成 input radio
 * <#dictRadio name="gender" dicts = "${genderDict}" value="${user.gender!}" valid="targetId"(非必选)/>
 * Created by kfc on 2016/7/7.
 */
public class DictRadioTag extends Tag {
    @Override
    public void render() {
        try {
            StringBuilder out = new StringBuilder();
            Map attrs = (Map) this.args[1];
            String name = (String) attrs.get("name");
            String value = (String) attrs.get("value");
            String valid = (String) attrs.get("valid");
            List<SysDict> dicts = (List<SysDict>) attrs.get("dicts");
            int i = 0;
            for (SysDict dict : dicts) {
                String rule = "";
                if (i == 0 && valid != null) {
                    rule = " data-rule='checked'  data-target='" + valid + "' ";
                }
                String checked = "";
                if (value != null && value.equals(dict.getDictCode())) {
                    checked = " checked ";
                }
               out.append("<div class='radio' style='float: left;margin-left:10px'> <label>");
                out.append("<input type='radio' name='" + name + "' value='"
                        + dict.getDictCode() + "' " + checked + rule + " />"
                        + dict.getDictName());
                out.append("</label></div> ");
                i++;
            }
            this.ctx.byteWriter.writeString(out.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
