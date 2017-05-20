package com.corngo.base.support.utils;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.toolkit.Sequence;
import com.corngo.base.support.spring.SpringContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * id工具
 *
 * @author kfc
 * @version 1.0 - 2016-10-14
 */
public class IdUtils {

    private static Sequence sequence = new Sequence();

    /**
     * oracle序列
     * @param clazz entity
     * @return long
     */
    public static long oracle(Class<?> clazz) {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextHolder.getBean("jdbcTemplate");
        String tableName = clazz.getAnnotation(TableName.class).value().toUpperCase();
        return jdbcTemplate.queryForObject("SELECT  SEQ_" + tableName + ".nextval FROM  DUAL", Long.class);
    }

    /**
     * 分布式id
     * @return long
     */
    public static long sequence(){
        return sequence.nextId();
    }

    /**
     * 分布式id
     * @return str
     */
    public static String sequenceString(){
        return sequence()+"";
    }
}
