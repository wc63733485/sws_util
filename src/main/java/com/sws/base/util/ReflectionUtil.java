package com.sws.base.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReflectionUtil {

    /**
     * 是否为某一类型
     *
     * @param field
     * @param clazz
     * @return
     */
    public static Boolean isType(Field field, Class clazz) {
        return field.getType().equals(clazz);
    }

    /**
     * 获取类名
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getClassName(T t) {
        Class clazz = t.getClass();
        return clazz.getSimpleName();
    }

    /**
     * 获取字段列表
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Field[] getFieldList(T t) {
        Class clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            Field[] superFields = superClazz.getDeclaredFields();

            Field[] c = new Field[declaredFields.length + superFields.length];

            System.arraycopy(declaredFields, 0, c, 0, declaredFields.length);

            System.arraycopy(superFields, 0, c, declaredFields.length, superFields.length);
            return c;
        }
        return declaredFields;
    }



    public static Field[] getSuperClassFields(Class<?> clazz) {
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            Field[] superFields = superClazz.getDeclaredFields();
            Field[] superClassFields = getSuperClassFields(superClazz);

            Field[] c = new Field[superFields.length + superClassFields.length];
            System.arraycopy(superFields, 0, c, 0, superFields.length);
            System.arraycopy(superClassFields, 0, c, superFields.length, superClassFields.length);

            return c;
        }
        Field[] f = new Field[0];
        return f;
    }
    /**
     * 强制获取字段值
     *
     * @param t
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T> Object getFieldValueForce(T t, String fieldName) {
        Class clazz = t.getClass();
        Object value = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            value = getFieldValue(t, field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 获取字段值
     *
     * @param t
     * @param field
     * @param <T>
     * @return
     */
    private static <T> Object getFieldValue(T t, Field field) {
        Object value = null;
        field.setAccessible(true);
        try {
            value = field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取注解信息
     *
     * @param t
     * @param annotationClass
     * @param <T>
     * @return
     */
    public static <T> Annotation getAnnotation(T t, Class annotationClass) {
        Class clazz = t.getClass();

        return clazz.getAnnotation(annotationClass);
    }

    /**
     * 获取注解信息
     *
     * @param field
     * @param annotationClass
     * @return
     */
    public static Annotation getAnnotation(Field field, Class annotationClass) {
        return field.getAnnotation(annotationClass);
    }
}
