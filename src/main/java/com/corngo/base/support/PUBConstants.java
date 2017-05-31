package com.corngo.base.support;

/**
 * 系统共用常量
 * <p/>
 * Created by kfc on 2016/6/29.
 */
public class PUBConstants {
    /**
     * 默认分页大小
     */
    public static final int DEFAULT_PAGESIZE = 10;

    /**
     * ajax成功返回码
     */
    public static final String SUCCESS_CODE = "1";
    /**
     * ajax失败返回码
     */
    public static final String ERROR_CODE = "-1";

    /**
     * JDK默认时间格式
     */
    public static final String DEFAULT_FORMAT_DATE_JDK = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";

    /**
     * joddDate时间格式
     */
    public static final String DEFAULT_FORMAT_DATE_JOD = "YYYY-MM-DD hh:mm:ss";

    /**
     * 可用状态
     */
    public static final String ENABLE_STATUS = "1";
    /**
     * 不可用状态
     */
    public static final String DISABLE_STATUS = "0";
    /**
     * biao标示
     */
    public static final String TOKEN = "_token";

    /**
     * 后台用户对象session
     */
    public static final String ADMIN_SESSION_USER_OBJ = "_admin_user_obj";
    /**
     * 后台用户Id session
     */
    public static final String ADMIN_SESSION_USER = "_admin_user";
    /**
     * 一级菜单
     */
    public static final String ADMIN_FIRST_MENU = "_first_menu";
    /**
     * 二级菜单
     */
    public static final String ADMIN_SECOND_MENU = "_second_menu";
    /**
     * 后台功能按钮
     */
    public static final String ADMIN_BUTTON = "_admin_button";
    /**
     * 资源权限
     */
    public static final String ADMIN_SOURCE_ALL = "_admin_source_all";
    /**
     * 功能按钮资源权限
     */
    public static final String ADMIN_SOURCE_REGX = "_admin_source_regx";

    /**
     * 系统调度配置
     */
    // 用户Excel导入调度任务
    public static final String JOB_USER_EXCEL_IMPORT_ = "JOB_USER_EXCEL_IMPORT_";
    public static final String JOB_USER_EXCEL_IMPORT_PREFIX_ = "JOB_USER_EXCEL_IMPORT_PREFIX_";
    public static final String JOB_USER_EXCEL_IMPORT_TRIGGER_PREFIX_ = "JOB_USER_EXCEL_IMPORT_TRIGGER_PREFIX_";

    /**
     * 商品状态
     */
    public static class GoodsStatus {
        public static final String INIT = "1";          //    初始化
        public static final String PUBLISHED = "2";   //    已发布
        public static final String COLOSED = "3";    //   已下架
    }

}
