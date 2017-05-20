package com.corngo.generate;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.baomidou.mybatisplus.generator.ConfigGenerator;

/**
 * mybatis-plus生成器修改，指定mapper.xml到src/main/resources, windows资源管理器不自动打开
 * @author kfc
 * @version 1.0 - 2016-10-14
 */
public class AutoGeneratorFix extends AbstractGeneratorFix {

    private VelocityEngine engine;


    public AutoGeneratorFix() {
    }

    /**
     * run 执行
     */
    public  void run(String output_dir_res,String resourceDir) {
        System.out.println("==========================准备生成文件...==========================");
        this.initConfig(output_dir_res,resourceDir);
        this.mkdirs(this.config.getPathInfo());
        Map ctxData = this.analyzeData(this.config);
        Iterator e = ctxData.entrySet().iterator();

        while(e.hasNext()) {
            Map.Entry ctx = (Map.Entry)e.next();
            this.batchOutput((String)ctx.getKey(), (VelocityContext)ctx.getValue());
        }

        if(this.config.getGlobalConfig().isOpen()) {
            try {
                String e1 = System.getProperty("os.name");
                if(e1 != null) {
                    if(e1.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + this.config.getGlobalConfig().getOutputDir());
                    } else if(e1.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + this.config.getGlobalConfig().getOutputDir());
                    } else {
                        System.out.println("文件输出目录:" + this.config.getGlobalConfig().getOutputDir());
                    }
                }
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        System.out.println("==========================文件生成完成！！！==========================");

    }
    private void mkdirs(Map<String, String> pathInfo) {
        Iterator var2 = pathInfo.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry entry = (Map.Entry)var2.next();
            File dir = new File((String)entry.getValue());
            if(!dir.exists()) {
                boolean result = dir.mkdirs();
                if(result) {
                    System.out.println("创建目录： [" + (String) entry.getValue() + "]");
                }
            }
        }

    }
    private Map<String, VelocityContext> analyzeData(ConfigBuilderFix config) {
        List tableList = config.getTableInfoList();
        Map packageInfo = config.getPackageInfo();
        HashMap ctxData = new HashMap();
        String superEntityClass = this.getSuperClassName(config.getSuperEntityClass());
        String superMapperClass = this.getSuperClassName(config.getSuperMapperClass());
        String superServiceClass = this.getSuperClassName(config.getSuperServiceClass());
        String superServiceImplClass = this.getSuperClassName(config.getSuperServiceImplClass());
        String superControllerClass = this.getSuperClassName(config.getSuperControllerClass());
        String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Iterator var12 = tableList.iterator();

        while(var12.hasNext()) {
            TableInfo tableInfo = (TableInfo)var12.next();
            VelocityContext ctx = new VelocityContext();
            if(null != this.injectionConfig) {
                this.injectionConfig.initMap();
                ctx.put("cfg", this.injectionConfig.getMap());
            }

            if(config.getGlobalConfig().isActiveRecord()) {
                tableInfo.setImportPackages("com.baomidou.mybatisplus.activerecord.Model");
            }

            if(tableInfo.isConvert()) {
                tableInfo.setImportPackages("com.baomidou.mybatisplus.annotations.TableName");
            }

            if(StringUtils.isNotEmpty(config.getSuperEntityClass())) {
                tableInfo.setImportPackages(config.getSuperEntityClass());
            } else {
                tableInfo.setImportPackages("java.io.Serializable");
            }

            ctx.put("package", packageInfo);
            ctx.put("author", config.getGlobalConfig().getAuthor());
            ctx.put("activeRecord", Boolean.valueOf(config.getGlobalConfig().isActiveRecord()));
            ctx.put("date", date);
            ctx.put("table", tableInfo);
            ctx.put("enableCache", Boolean.valueOf(config.getGlobalConfig().isEnableCache()));
            ctx.put("baseResultMap", Boolean.valueOf(config.getGlobalConfig().isBaseResultMap()));
            ctx.put("baseColumnList", Boolean.valueOf(config.getGlobalConfig().isBaseColumnList()));
            ctx.put("entity", tableInfo.getEntityName());
            ctx.put("entityColumnConstant", Boolean.valueOf(config.getStrategyConfig().isEntityColumnConstant()));
            ctx.put("entityBuilderModel", Boolean.valueOf(config.getStrategyConfig().isEntityBuilderModel()));
            ctx.put("superEntityClass", superEntityClass);
            ctx.put("superMapperClassPackage", config.getSuperMapperClass());
            ctx.put("superMapperClass", superMapperClass);
            ctx.put("superServiceClassPackage", config.getSuperServiceClass());
            ctx.put("superServiceClass", superServiceClass);
            ctx.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
            ctx.put("superServiceImplClass", superServiceImplClass);
            ctx.put("superControllerClassPackage", config.getSuperControllerClass());
            ctx.put("superControllerClass", superControllerClass);
            ctxData.put(tableInfo.getEntityName(), ctx);
        }

        return ctxData;
    }
    private String getSuperClassName(String classPath) {
        return StringUtils.isEmpty(classPath)?null:classPath.substring(classPath.lastIndexOf(".") + 1);
    }

    private void batchOutput(String entityName, VelocityContext context) {
        try {
            TableInfo e = (TableInfo)context.get("table");
            Map pathInfo = this.config.getPathInfo();
            String entityFile = String.format((String)pathInfo.get("entity_path") + ConstVal.ENTITY_NAME, new Object[]{entityName});
            String mapperFile = String.format((String)pathInfo.get("mapper_path") + File.separator + e.getMapperName() + ".java", new Object[]{entityName});
            String xmlFile = String.format((String)pathInfo.get("xml_path") + File.separator + e.getXmlName() + ".xml", new Object[]{entityName});
            String serviceFile = String.format((String)pathInfo.get("serivce_path") + File.separator + e.getServiceName() + ".java", new Object[]{entityName});
            String implFile = String.format((String)pathInfo.get("serviceimpl_path") + File.separator + e.getServiceImplName() + ".java", new Object[]{entityName});
            String controllerFile = String.format((String)pathInfo.get("controller_path") + File.separator + e.getControllerName() + ".java", new Object[]{entityName});
            TemplateConfig template = this.config.getTemplate();
            if(this.isCreate(entityFile)) {
                this.vmToFile(context, template.getEntity(), entityFile);
            }

            if(this.isCreate(mapperFile)) {
                this.vmToFile(context, template.getMapper(), mapperFile);
            }

            if(this.isCreate(xmlFile)) {
                this.vmToFile(context, template.getXml(), xmlFile);
            }

            if(this.isCreate(serviceFile)) {
                this.vmToFile(context, template.getService(), serviceFile);
            }

            if(this.isCreate(implFile)) {
                this.vmToFile(context, template.getServiceImpl(), implFile);
            }

            if(this.isCreate(controllerFile)) {
                this.vmToFile(context, template.getController(), controllerFile);
            }

            if(this.injectionConfig != null) {
                List focList = this.injectionConfig.getFileOutConfigList();
                if(CollectionUtils.isNotEmpty(focList)) {
                    Iterator var13 = focList.iterator();

                    while(var13.hasNext()) {
                        FileOutConfig foc = (FileOutConfig)var13.next();
                        this.vmToFile(context, foc.getTemplatePath(), foc.outputFile(e));
                    }
                }
            }
        } catch (IOException var15) {
            System.out.println("无法创建文件，请检查配置信息！");
        }

    }


    private boolean isCreate(String filePath) {
        File file = new File(filePath);
        return !file.exists() || this.config.getGlobalConfig().isFileOverride();
    }
    private void vmToFile(VelocityContext context, String templatePath, String outputFile) throws IOException {
        if(!StringUtils.isEmpty(templatePath)) {
            VelocityEngine velocity = this.getVelocityEngine();
            Template template = velocity.getTemplate(templatePath, ConstVal.UTF8);
            File file = new File(outputFile);
            if(!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                System.out.println("创建文件所在的目录失败!");
            } else {
                FileOutputStream fos = new FileOutputStream(outputFile);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
                template.merge(context, writer);
                writer.close();
                System.out.println("模板:" + templatePath + ";  文件:" + outputFile);
            }
        }
    }
    private VelocityEngine getVelocityEngine() {
        if(this.engine == null) {
            Properties p = new Properties();
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("file.resource.loader.path", "");
            p.setProperty("ISO-8859-1", ConstVal.UTF8);
            p.setProperty("input.encoding", ConstVal.UTF8);
            p.setProperty("output.encoding", ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
            this.engine = new VelocityEngine(p);
        }

        return this.engine;
    }
}
