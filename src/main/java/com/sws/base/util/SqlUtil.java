package com.sws.base.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sws.base.util.ReflectionUtil.getFieldList;

public class SqlUtil {

    /**
     * 插入模板
     */
    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s ) ;";

    /**
     * 查询模板
     */
    private static final String SELECT_FORMAT = "SELECT * FROM %s WHERE %s LIMIT %s,%s ;";

    /**
     * 构建插入语句
     *
     * @param obj
     * @return
     */
    public String BaseInsert(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2Str(FieldUtil.getFields(obj));
        String v = StringUtil.concatCollection2Str(FieldUtil.getFieldsValues(obj));
        return String.format(INSERT_FORMAT, tableName, s, v);
    }

    /**
     * 构建插入语句
     *
     * @param obj
     * @return
     */
    public String BaseQuery(Object obj,int page,int limit) {
        String tableName = TableUtil.getTableName(obj);

        String s = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj));
        return String.format(SELECT_FORMAT, tableName, s, page, limit);
    }
}
