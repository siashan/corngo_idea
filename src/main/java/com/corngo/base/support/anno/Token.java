package com.corngo.base.support.anno;

import com.corngo.base.support.enums.TokenAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防表单重复提交注解
 *      *进入表单页面时在方法上使用注解@Token(TokenAction.GET);
 *      *提交表单页面时在方法上使用注解@Token(TokenAction.VALID)
 * Created by kfc on 2016/7/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    TokenAction value() default TokenAction.GET;
}
