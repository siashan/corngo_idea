package com.corngo.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class MySQLFixedPlugin extends PluginAdapter {

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		element.addAttribute(new Attribute("keyProperty", "id"));
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}
	
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

}
