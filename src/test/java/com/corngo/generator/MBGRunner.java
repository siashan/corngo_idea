package com.corngo.generator;

import com.eletaxi.base.generator.plugins.BJDGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 * <p>
 * <b>运行前请检查generator-config.xml中的表配置，以免覆盖现有代码！！！</b>
 * </p>
 * Created by kfc on 2015/1/5.
 */
public class MBGRunner {

	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(System.getProperty("user.dir") + "/src/test/resources/generator-config.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = null;
		try {
			config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			BJDGenerator myBatisGenerator = new BJDGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println("OK！");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

}
