package com.corngo.base.support.anno;

import java.lang.annotation.*;

/**
 * session获取参数 注解
 * @Author kfc
 * @Date 2017/6/27 11:28
 */
@Target({ ElementType.PARAMETER,ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionScope {
    String value();
}
