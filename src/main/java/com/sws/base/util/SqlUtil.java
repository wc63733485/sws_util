package com.sws.base.util;

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
     * 删除模板
     */
    private static final String DELETE_FORMAT = "DELETE FROM %s WHERE %s;";

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
     * 构建查询语句
     *
     * @param obj
     * @return
     */
    public String BaseQuery(Object obj,int page,int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj));
        return String.format(SELECT_FORMAT, tableName, s, page, limit);
    }


    /**
     * 构建删除语句
     *
     * @param obj
     * @return
     */
    public String BaseDelete(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrEquals(FieldUtil.getNotNullFiledString(obj));
        return String.format(DELETE_FORMAT, tableName, s);
    }
}
