package com.corngo.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by kfc on 2015/3/13.
 */
public class OracleFixedPlugin extends PluginAdapter {

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement keyElement = new XmlElement("selectKey");
        keyElement.addAttribute(new Attribute("resultType", "java.lang.Long"));
        keyElement.addAttribute(new Attribute("keyProperty", "id"));
        keyElement.addAttribute(new Attribute(" order", "BEFORE"));
        keyElement.addElement(new TextElement("SELECT SEQ_"+introspectedTable.getFullyQualifiedTableNameAtRuntime()+".NEXTVAL FROM DUAL"));
        element.addElement(0, keyElement);
        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}
