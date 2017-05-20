package com.corngo.generate;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.rules.QuerySQL;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by kfc on 2017/5/4.
 */
public class ConfigBuilderFix {
    private Connection connection;
    private QuerySQL querySQL;
    private String superEntityClass;
    private String superMapperClass;
    private String superServiceClass;
    private String superServiceImplClass;
    private String superControllerClass;
    private List<TableInfo> tableInfoList;
    private Map<String, String> packageInfo;
    private Map<String, String> pathInfo;
    private TemplateConfig template;
    private DataSourceConfig dataSourceConfig;
    private StrategyConfig strategyConfig;
    private GlobalConfig globalConfig;

    public ConfigBuilderFix(PackageConfig packageConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                            TemplateConfig template, GlobalConfig globalConfig,String resourceDir ,String output_dir_res) {
        if(null == globalConfig) {
            this.globalConfig = new GlobalConfig();
        } else {
            this.globalConfig = globalConfig;
        }

        if(null == template) {
            this.template = new TemplateConfig();
        } else {
            this.template = template;
        }

        if(null == packageConfig) {
            this.handlerPackage(this.template, this.globalConfig.getOutputDir(), new PackageConfig(),resourceDir,output_dir_res);
        } else {
            this.handlerPackage(this.template, this.globalConfig.getOutputDir(), packageConfig,resourceDir,output_dir_res);
        }

        this.dataSourceConfig = dataSourceConfig;
        this.handlerDataSource(dataSourceConfig);
        if(null == strategyConfig) {
            this.strategyConfig = new StrategyConfig();
        } else {
            this.strategyConfig = strategyConfig;
        }

        this.handlerStrategy(this.strategyConfig);
    }

    public Map<String, String> getPackageInfo() {
        return this.packageInfo;
    }

    public Map<String, String> getPathInfo() {
        return this.pathInfo;
    }

    public String getSuperEntityClass() {
        return this.superEntityClass;
    }

    public String getSuperMapperClass() {
        return this.superMapperClass;
    }

    public String getSuperServiceClass() {
        return this.superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return this.superServiceImplClass;
    }

    public String getSuperControllerClass() {
        return this.superControllerClass;
    }

    public List<TableInfo> getTableInfoList() {
        return this.tableInfoList;
    }

    public TemplateConfig getTemplate() {
        return this.template == null?new TemplateConfig():this.template;
    }

    private void handlerPackage(TemplateConfig template, String outputDir, PackageConfig config,String resourceDir,String output_dir_res) {
        this.packageInfo = new HashMap();
        this.packageInfo.put("ModuleName", config.getModuleName());
        this.packageInfo.put("Entity", this.joinPackage(config.getParent(), config.getEntity()));
        this.packageInfo.put("Mapper", this.joinPackage(config.getParent(), config.getMapper()));
        this.packageInfo.put("Xml", this.joinPackage(resourceDir, config.getXml()));
        this.packageInfo.put("Service", this.joinPackage(config.getParent(), config.getService()));
        this.packageInfo.put("ServiceImpl", this.joinPackage(config.getParent(), config.getServiceImpl()));
        this.packageInfo.put("Controller", this.joinPackage(config.getParent(), config.getController()));
        this.pathInfo = new HashMap();
        if(StringUtils.isNotEmpty(template.getEntity())) {
            this.pathInfo.put("entity_path", this.joinPath(outputDir, (String)this.packageInfo.get("Entity")));
        }

        if(StringUtils.isNotEmpty(template.getMapper())) {
            this.pathInfo.put("mapper_path", this.joinPath(outputDir, (String)this.packageInfo.get("Mapper")));
        }

        if(StringUtils.isNotEmpty(template.getXml())) {
            this.pathInfo.put("xml_path", this.joinPath(output_dir_res, (String)this.packageInfo.get("Xml")));
        }

        if(StringUtils.isNotEmpty(template.getService())) {
            this.pathInfo.put("serivce_path", this.joinPath(outputDir, (String)this.packageInfo.get("Service")));
        }

        if(StringUtils.isNotEmpty(template.getServiceImpl())) {
            this.pathInfo.put("serviceimpl_path", this.joinPath(outputDir, (String)this.packageInfo.get("ServiceImpl")));
        }

        if(StringUtils.isNotEmpty(template.getController())) {
            this.pathInfo.put("controller_path", this.joinPath(outputDir, (String)this.packageInfo.get("Controller")));
        }

    }

    private void handlerDataSource(DataSourceConfig config) {
        this.connection = config.getConn();
        this.querySQL = this.getQuerySQL(config.getDbType());
    }

    private void handlerStrategy(StrategyConfig config) {
        this.processTypes(config);
        this.tableInfoList = this.getTablesInfo(config);
    }

    private void processTypes(StrategyConfig config) {
        if(StringUtils.isEmpty(config.getSuperServiceClass())) {
            this.superServiceClass = "com.baomidou.mybatisplus.service.IService";
        } else {
            this.superServiceClass = config.getSuperServiceClass();
        }

        if(StringUtils.isEmpty(config.getSuperServiceImplClass())) {
            this.superServiceImplClass = "com.baomidou.mybatisplus.service.impl.ServiceImpl";
        } else {
            this.superServiceImplClass = config.getSuperServiceImplClass();
        }

        if(StringUtils.isEmpty(config.getSuperMapperClass())) {
            this.superMapperClass = "com.baomidou.mybatisplus.mapper.BaseMapper";
        } else {
            this.superMapperClass = config.getSuperMapperClass();
        }

        this.superEntityClass = config.getSuperEntityClass();
        this.superControllerClass = config.getSuperControllerClass();
    }

    private List<TableInfo> processTable(List<TableInfo> tableList, NamingStrategy strategy, String[] tablePrefix) {
        Iterator var4 = tableList.iterator();

        while(var4.hasNext()) {
            TableInfo tableInfo = (TableInfo)var4.next();
            tableInfo.setEntityName(this.strategyConfig, NamingStrategy.capitalFirst(this.processName(tableInfo.getName(), strategy, tablePrefix)));
            if(StringUtils.isNotEmpty(this.globalConfig.getMapperName())) {
                tableInfo.setMapperName(String.format(this.globalConfig.getMapperName(), new Object[]{tableInfo.getEntityName()}));
            } else {
                tableInfo.setMapperName(tableInfo.getEntityName() + "Mapper");
            }

            if(StringUtils.isNotEmpty(this.globalConfig.getXmlName())) {
                tableInfo.setXmlName(String.format(this.globalConfig.getXmlName(), new Object[]{tableInfo.getEntityName()}));
            } else {
                tableInfo.setXmlName(tableInfo.getEntityName() + "Mapper");
            }

            if(StringUtils.isNotEmpty(this.globalConfig.getServiceName())) {
                tableInfo.setServiceName(String.format(this.globalConfig.getServiceName(), new Object[]{tableInfo.getEntityName()}));
            } else {
                tableInfo.setServiceName("I" + tableInfo.getEntityName() + "Service");
            }

            if(StringUtils.isNotEmpty(this.globalConfig.getServiceImplName())) {
                tableInfo.setServiceImplName(String.format(this.globalConfig.getServiceImplName(), new Object[]{tableInfo.getEntityName()}));
            } else {
                tableInfo.setServiceImplName(tableInfo.getEntityName() + "ServiceImpl");
            }

            if(StringUtils.isNotEmpty(this.globalConfig.getControllerName())) {
                tableInfo.setControllerName(String.format(this.globalConfig.getControllerName(), new Object[]{tableInfo.getEntityName()}));
            } else {
                tableInfo.setControllerName(tableInfo.getEntityName() + "Controller");
            }
        }

        return tableList;
    }

    private List<TableInfo> getTablesInfo(StrategyConfig config) {
        boolean isInclude = null != config.getInclude() && config.getInclude().length > 0;
        boolean isExclude = null != config.getExclude() && config.getExclude().length > 0;
        if(isInclude && isExclude) {
            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        } else {
            ArrayList tableList = new ArrayList();
            ArrayList includeTableList = new ArrayList();
            ArrayList excludeTableList = new ArrayList();
            HashSet notExistTables = new HashSet();
            NamingStrategy strategy = config.getNaming();
            PreparedStatement pstate = null;

            try {
                pstate = this.connection.prepareStatement(this.querySQL.getTableCommentsSql());
                ResultSet e = pstate.executeQuery();

                while(true) {
                    while(e.next()) {
                        String tableName = e.getString(this.querySQL.getTableName());
                        if(StringUtils.isNotEmpty(tableName)) {
                            String tabInfo = e.getString(this.querySQL.getTableComment());
                            TableInfo tableInfo = new TableInfo();
                            tableInfo.setName(tableName);
                            tableInfo.setComment(tabInfo);
                            String[] fieldList;
                            int var15;
                            int var16;
                            String excludeTab;
                            if(isInclude) {
                                fieldList = config.getInclude();
                                var15 = fieldList.length;

                                for(var16 = 0; var16 < var15; ++var16) {
                                    excludeTab = fieldList[var16];
                                    if(excludeTab.equalsIgnoreCase(tableName)) {
                                        includeTableList.add(tableInfo);
                                    } else {
                                        notExistTables.add(excludeTab);
                                    }
                                }
                            } else if(isExclude) {
                                fieldList = config.getExclude();
                                var15 = fieldList.length;

                                for(var16 = 0; var16 < var15; ++var16) {
                                    excludeTab = fieldList[var16];
                                    if(excludeTab.equalsIgnoreCase(tableName)) {
                                        excludeTableList.add(tableInfo);
                                    } else {
                                        notExistTables.add(excludeTab);
                                    }
                                }
                            }

                            List var30 = this.getListFields(tableName, strategy);
                            tableInfo.setFields(var30);
                            tableList.add(tableInfo);
                        } else {
                            System.err.println("当前数据库为空！！！");
                        }
                    }

                    Iterator var28 = tableList.iterator();

                    while(var28.hasNext()) {
                        TableInfo var29 = (TableInfo)var28.next();
                        notExistTables.remove(var29.getName());
                    }

                    if(notExistTables.size() > 0) {
                        System.err.println("表 " + notExistTables + " 在数据库中不存在！！！");
                    }

                    if(isExclude) {
                        tableList.removeAll(excludeTableList);
                        includeTableList = tableList;
                    }

                    if(!isInclude && !isExclude) {
                        includeTableList = tableList;
                    }
                    break;
                }
            } catch (SQLException var26) {
                var26.printStackTrace();
            } finally {
                try {
                    if(pstate != null) {
                        pstate.close();
                    }

                    if(this.connection != null) {
                        this.connection.close();
                    }
                } catch (SQLException var25) {
                    var25.printStackTrace();
                }

            }

            return this.processTable(includeTableList, strategy, config.getTablePrefix());
        }
    }

    private boolean isKeyIdentity(ResultSet results) throws SQLException {
        if(QuerySQL.MYSQL == this.querySQL) {
            String extra = results.getString("Extra");
            if("auto_increment".equals(extra)) {
                return true;
            }
        }

        return false;
    }

    private List<TableField> getListFields(String tableName, NamingStrategy strategy) {
        boolean haveId = false;
        ArrayList fieldList = new ArrayList();

        try {
            PreparedStatement e = this.connection.prepareStatement(String.format(this.querySQL.getTableFieldsSql(), new Object[]{tableName}));
            Throwable var6 = null;

            try {
                ResultSet results = e.executeQuery();
                Throwable var8 = null;

                try {
                    while(results.next()) {
                        TableField field = new TableField();
                        String key = results.getString(this.querySQL.getFieldKey());
                        boolean isId = StringUtils.isNotEmpty(key) && key.toUpperCase().equals("PRI");
                        if(isId && !haveId) {
                            field.setKeyFlag(true);
                            if(this.isKeyIdentity(results)) {
                                field.setKeyIdentityFlag(true);
                            }

                            haveId = true;
                        } else {
                            field.setKeyFlag(false);
                        }

                        field.setName(results.getString(this.querySQL.getFieldName()));
                        if(!this.strategyConfig.includeSuperEntityColumns(field.getName())) {
                            field.setType(results.getString(this.querySQL.getFieldType()));
                            field.setPropertyName(this.strategyConfig, this.processName(field.getName(), strategy));
                            field.setColumnType(this.dataSourceConfig.getTypeConvert().processTypeConvert(field.getType()));
                            field.setComment(results.getString(this.querySQL.getFieldComment()));
                            fieldList.add(field);
                        }
                    }
                } catch (Throwable var35) {
                    var8 = var35;
                    throw var35;
                } finally {
                    if(results != null) {
                        if(var8 != null) {
                            try {
                                results.close();
                            } catch (Throwable var34) {
                                var8.addSuppressed(var34);
                            }
                        } else {
                            results.close();
                        }
                    }

                }
            } catch (Throwable var37) {
                var6 = var37;
                throw var37;
            } finally {
                if(e != null) {
                    if(var6 != null) {
                        try {
                            e.close();
                        } catch (Throwable var33) {
                            var6.addSuppressed(var33);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var39) {
            System.err.println("SQL Exception：" + var39.getMessage());
        }

        return fieldList;
    }

    private String joinPath(String parentDir, String packageName) {
        if(StringUtils.isEmpty(parentDir)) {
            parentDir = System.getProperty("java.io.tmpdir");
        }

        if(!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir = parentDir + File.separator;
        }

        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }

    private String joinPackage(String parent, String subPackage) {
        return StringUtils.isEmpty(parent)?subPackage:parent + "." + subPackage;
    }

    private String processName(String name, NamingStrategy strategy) {
        return this.processName(name, strategy, (String[])null);
    }

    private String processName(String name, NamingStrategy strategy, String[] tablePrefix) {
        boolean removePrefix = false;
        if(tablePrefix != null && tablePrefix.length >= 1) {
            removePrefix = true;
        }

        String propertyName;
        if(removePrefix) {
            if(strategy == NamingStrategy.underline_to_camel) {
                propertyName = NamingStrategy.removePrefixAndCamel(name, tablePrefix);
            } else {
                propertyName = NamingStrategy.removePrefix(name, tablePrefix);
            }
        } else if(strategy == NamingStrategy.underline_to_camel) {
            propertyName = NamingStrategy.underlineToCamel(name);
        } else {
            propertyName = name;
        }

        return propertyName;
    }

    private QuerySQL getQuerySQL(DbType dbType) {
        QuerySQL[] var2 = QuerySQL.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            QuerySQL qs = var2[var4];
            if(qs.getDbType().equals(dbType.getValue())) {
                return qs;
            }
        }

        return QuerySQL.MYSQL;
    }

    public StrategyConfig getStrategyConfig() {
        return this.strategyConfig;
    }

    public void setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

}
