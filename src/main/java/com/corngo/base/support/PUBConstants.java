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
     * 用户任务状态
     */
    public static class UserSchedulStatus {
        public static final String ST_INIT = "INIT";
        public static final String ST_FINISH = "FINISH";
        public static final String COLOR = "yellow";
        public static final String TEXT_COLOR = "black";
    }

    /**
     * 订单状态
     */
    public static class OrderInfoStatus {
        public static final int ST_INIT = 0;   //  待接单
        public static final int ST_INORDER = 1;   //  已接单/待出发
        public static final int ST_ON_THE_WAY = 2;   //  已出发
        public static final int ST_DONE = 3;   //  已结束
        public static final int ST_CANCEL = 4;   //  已取消
    }

    /**
     * 订单支付状态
     */
    public static class OrderPayStatus {
        public static final int INIT = 0;   //  待支付
        public static final int PAYING = 1;   //  支付中
        public static final int FAIL = 2;   //  支付失败
        public static final int SUCCESS = 3;   //  已支付
        public static final int ST_CANCEL = 4;   //  已取消
    }

    /**
     * 验证码状态
     */
    public static class VerfifyCodeStatus{

        public static final int ST_YES = 1;  // 无效
        public static final int ST_NO = 0; // 有效


    }

    /**
     * 司机状态
     *
     */
    public static class DriverStatus{
        public static final int WAIT_AUDIT = 1;  // 待审核
        public static final int AUDITING = 2;  // 审核中
        public static final int AUDIT_REJECT = 3;  // 审核拒绝
        public static final int AUDIT_SUCCESS  = 4;  // 审核通过

    }


}
