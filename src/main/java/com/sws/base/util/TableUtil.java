package com.sws.base.util;


import com.sws.base.annotations.Entity;

import java.lang.annotation.Annotation;

/**
 * 列表工具类
 *
 * @author wxc
 * @date 20/1/14
 */
public class TableUtil {
    private TableUtil() {
    }

    /**
     * 获取表名称
     *
     * @param t
     * @param <T>
     * @return 表名称
     */
    public static <T> String getTableName(T t) {
        Annotation annotation = ReflectionUtil.getAnnotation(t, Entity.class);

        String tableName = ReflectionUtil.getClassName(t);

        if (annotation != null) {
            Entity entity = (Entity) annotation;
            String tableValue = entity.value();
            tableName = StringUtil.isEmpty(tableValue) ? tableName : tableValue;
        }

        return tableName;
    }

    /**
     * 获取表名称
     *
     * @param t
     * @param <T>
     * @return 表名称
     */
    public static <T> String getTableNameClass(T t) {
        Annotation annotation = ReflectionUtil.getAnnotation(t, Entity.class);

        String tableName = ReflectionUtil.getClassName(t);

        if (annotation != null) {
            Entity entity = (Entity) annotation;
            String tableValue = entity.value();
            tableName = StringUtil.isEmpty(tableValue) ? tableName : tableValue;
        }

        return tableName;
    }
}