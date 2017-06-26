package com.corngo.generate;


import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 代码生成器配置
 *
 * @author kfc
 * @version 1.0 - 2016-10-14
 */
public class GenerateCode {
    /* 生成代码包名 */
    private static final String PACKAGE_NAME = "com.corngo";
    /* 生成代码模块名 */
    private static final String MODEL_NAME = "corn";

    private static final String MODEL_NAME_RES = "corn";
    /* 生成文件保存位置*/
    private static final String OUTPUT_DIR = getRootPath() + "/src/main/java";
    /* 生成xml文件保存位置*/
    private static final String OUTPUT_DIR_RES = getRootPath() + "/src/main";
    /*xml文件路径*/
    private static  String resourceDir =  "/resources";
    /*要生成的数据表名 【表名必须为大写】*/
//    private static final String[]  TABLE_PREFIX = new String[] { "MOBILE_" }; // 此处可以修改为您的表前缀
    private static final String[] TABLE_NAMES = new String[]{"USERS"};
    protected static String PATH_ENTITY = null;
    protected static String PATH_MAPPER = null;
    protected static String PATH_XML = null;
    protected static String PATH_SERVICE = null;
    protected static String PATH_SERVICE_IMPL = null;
    protected static String PATH_CONTROLLER_IMPL = null;


    public static void main(String[] args) {
        /* 获取 JDBC 配置文件 */
        Properties props = getProperties();

        /* 配置 Mybatis-Plus 代码生成器 */
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUTPUT_DIR);   // 生成文件保存位置
        gc.setFileOverride(true);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("hanfc");    // 作者名称


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new OracleTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName(props.getProperty("jdbc_driverClassName"));
        dsc.setUrl(props.getProperty("jdbc_url"));
        dsc.setUsername(props.getProperty("jdbc_username"));
        dsc.setPassword(props.getProperty("jdbc_password"));


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
//        strategy.setTablePrefix(TABLE_PREFIX);// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(TABLE_NAMES); // 需要生成的表

        // 包配置
        PackageConfig pc = new PackageConfig();
        /**
         * 检查文件夹是否存在
         */
        File gf = new File(OUTPUT_DIR);
        if (!gf.exists()) {
            gf.mkdirs();
        }
        PATH_ENTITY = "model";
        PATH_MAPPER = "mapper";
        PATH_XML = "mybatis." + MODEL_NAME_RES;
        PATH_SERVICE = "service";
        PATH_SERVICE_IMPL = "service.impl";
        PATH_CONTROLLER_IMPL = "controller";
        pc.setParent(PACKAGE_NAME);
        pc.setModuleName(MODEL_NAME);
        pc.setEntity(PATH_ENTITY);
        pc.setMapper(PATH_MAPPER);
        pc.setXml(PATH_XML);
        pc.setService(PATH_SERVICE);
        pc.setServiceImpl(PATH_SERVICE_IMPL);
        pc.setController(PATH_CONTROLLER_IMPL);

        /* 生成代码 */
        AutoGeneratorFix autoGeneratorFix =  new AutoGeneratorFix();
        autoGeneratorFix.setGlobalConfig(gc);
        autoGeneratorFix.setDataSource(dsc);
        autoGeneratorFix.setStrategy(strategy);
        autoGeneratorFix.setPackageInfo(pc);
        autoGeneratorFix.run(OUTPUT_DIR_RES,resourceDir);
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/config.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 获取项目根路径
     *
     * @return 项目路径
     */
    private static String getRootPath() {
        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseFile;
    }


    protected static String getFilePath(String savePath, String segment) {
        File folder = new File(savePath + File.separator + segment);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        return folder.getPath();
    }
    protected static String getPathFromPackageName(String packageName) {
        return StringUtils.isEmpty(packageName)?"":packageName.replace(".", File.separator);
    }
}