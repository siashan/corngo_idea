package com.corngo.base.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 有象工具类
 *
 * Created by kfc on 2017/5/15.
 */
public class Corngo {
    private static Logger logger = LoggerFactory.getLogger(Corngo.class);
    /**
     * 随机数字
     */
    public static final String ALL_NUMBERS = "0123456789";

    private static Random r = new Random();

    /**
     * 生产N位长度的验证码
     *
     * @param length
     * @return
     */
    public static String captcha(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += ALL_NUMBERS.charAt(r.nextInt(ALL_NUMBERS.length()));
        }
        return str;
    }
}