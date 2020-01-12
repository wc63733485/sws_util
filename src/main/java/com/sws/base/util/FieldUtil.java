package com.sws.base.util;


import com.sws.base.Entity.PumpHouse;
import com.sws.base.annotations.Column;
import com.sws.base.annotations.GenerateValue;
import com.sws.base.annotations.Id;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.sws.base.util.ReflectionUtil.*;

public class FieldUtil {
    /**
     * 获取对应SQL字段类型
     *
     * @param field
     * @return
     */
    public static String getSqlType(Field field) {
        return TypeMap.getTypeMap().get(field.getType().getTypeName());
    }

    /**
     * 获取字段名称
     *
     * @param field
     * @return
     */
    public static String getFieldName(Field field) {
        String fieldName = field.getName();
        Annotation annotation = getAnnotation(field, Column.class);

        Column column = getFieldColumnAnnotation(field);
        if (column != null) {
            String columnValue = column.value();
            fieldName = StringUtil.isEmpty(columnValue) ? fieldName : columnValue;
        }

        return fieldName;
    }

    /**
     * 获取字段注解信息
     *
     * @param field
     * @return
     */
    public static Column getFieldColumnAnnotation(Field field) {
        Annotation annotation = getAnnotation(field, Column.class);

        if (annotation != null) {
            return (Column) annotation;
        }

        return null;
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
     * 获取字段名称列表
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> getFieldNameList(T t) {
        List<String> fieldNameList = new LinkedList<>();

        for (Field field : getFieldList(t)) {
            fieldNameList.add(getFieldName(field));
        }

        return fieldNameList;
    }


    /**
     * 获取字段名称字符串形式
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map getFieldNameString(T t) {
        Map<Object,Object> map = new HashMap<>();
        Class clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] superClassFields = getSuperClassFields(clazz);
        Field[] c = new Field[declaredFields.length + superClassFields.length];
        System.arraycopy(declaredFields, 0, c, 0, declaredFields.length);
        System.arraycopy(superClassFields, 0, c, declaredFields.length, superClassFields.length);

        for (Field field : c) {
            String key = field.getName();
            String getMethodStr = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method m = null;
            try {
                m = t.getClass().getMethod(getMethodStr);
                Object value = m.invoke(t);
                map.put(key,value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取字段值字符串形式
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getFieldValueString(T t) {
        List<String> valueStrList = new LinkedList<String>();
        for (Field field : getFieldList(t)) {
            valueStrList.add(String.format("'%s'", getValueString(t, field)));
        }

        return CollectionUtil.concatCollection2Str(valueStrList);
    }


    /**
     * 获取指定字段值字符串
     *
     * @param t
     * @param field
     * @param <T>
     * @return
     */
    private static <T> String getValueString(T t, Field field) {
        Object value = getFieldValueForce(t, field.getName());

        String result = value.toString();

        if (isType(field, Date.class)) {
            result = ((Date) value).toString();
        }

        return result;
    }

}
