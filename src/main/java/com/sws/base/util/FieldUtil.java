package com.sws.base.util;

import com.sws.base.annotations.Column;
import com.sws.base.annotations.GenerateValue;
import com.sws.base.annotations.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import static com.sws.base.util.ReflectionUtil.*;

public class FieldUtil {

    /**
     * 获取字段注解信息
     *
     * @param field
     * @return
     */
    public static String getFieldColumnAnnotation(Field field) {
        Annotation annotation = getAnnotation(field, Column.class);

        if (annotation != null) {
            return ((Column) annotation).value();
        }

        return field.getName();
    }

    /**
     * 获取ID字段
     * - 不存在则返回null
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Field getIdField(T t) {
        for (Field field : getFieldList(t)) {
            Annotation annotation = getAnnotation(field, Id.class);
            if (annotation != null) {
                return field;
            }
        }

        return null;
    }

    /**
     * 获取自增长字段。
     *
     * @param <T>
     * @return
     */
    public static <T> Field getGenerateValueField(T t) {
        for (Field field : getFieldList(t)) {
            Annotation annotation = getAnnotation(field, GenerateValue.class);
            if (annotation != null) {
                return field;
            }
        }

        return null;
    }

    /**
     * 是否为 GenerateValue 字段
     *
     * @param t
     * @param field
     * @param <T>
     * @return
     */
    public static <T> boolean isGenerateValueField(T t, Field field) {
        Field generateValueField = getGenerateValueField(t);
        return generateValueField != null && field.equals(generateValueField);
    }


    /**
     * 获取字段名称字符串
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List getFields(T t) {
        ArrayList arrayList = new ArrayList();

        Class clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] superClassFields = getSuperClassFields(clazz);

        for (Field field : declaredFields) {
            String annotationKey = getFieldColumnAnnotation(field);
            arrayList.add(annotationKey);
        }

        for (Field field : superClassFields) {
            String annotationKey = getFieldColumnAnnotation(field);
            arrayList.add(annotationKey);
        }
        return arrayList;
    }

    /**
     * 获取字段名称字符串
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List getFieldsValues(T t) {
        ArrayList arrayList = new ArrayList();

        Class clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] superClassFields = getSuperClassFields(clazz);

        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (java.lang.String.class.equals(field.getType()) && null!=o) {
                    o = "'" + o + "'";
                }
                arrayList.add(String.valueOf(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (Field field : superClassFields) {
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (java.lang.String.class.equals(field.getType()) && null!=o) {
                    o = "'" + o + "'";
                }
                arrayList.add(String.valueOf(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static <T> List getNotNullFiledString(T t) {
        ArrayList arrayList = new ArrayList();

        Class clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] superClassFields = getSuperClassFields(clazz);

        for (Field field : declaredFields) {
            String annotationKey = getFieldColumnAnnotation(field);
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (null==o) {
                    continue;
                }
                if (java.lang.String.class.equals(field.getType()) && null!=o) {
                    o = "'" + o + "'";
                }
                arrayList.add(annotationKey + "=" + String.valueOf(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (Field field : superClassFields) {
            String annotationKey = getFieldColumnAnnotation(field);
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (null==o) {
                    continue;
                }
                if (java.lang.String.class.equals(field.getType()) && null!=o) {
                    o = "'" + o + "'";
                }
                arrayList.add(annotationKey + "=" + String.valueOf(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
