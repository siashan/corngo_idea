package com.corngo.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class CachePlugin extends PluginAdapter {
	//private static final String MEMCACHED = "org.mybatis.caches.memcached.MemcachedCache";
	private static final String EHCACHE = "org.mybatis.caches.ehcache.EhcacheCache";

	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();
		XmlElement cache = new XmlElement("cache");
		cache.addAttribute(new Attribute("type", EHCACHE));
		parentElement.addElement(0, cache);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

}
