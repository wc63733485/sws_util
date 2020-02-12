package com.sws.base.util;

public class SqlUtil {

    /**
     * 插入模板
     */
    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s );";

    /**
     * 查询模板
     */
    private static final String SELECT_FORMAT = "SELECT * FROM %s WHERE %s LIMIT %s,%s;";


    /**
     * 查询模板不分页
     */
    private static final String SELECT_FORMAT_NOPAGE = "SELECT * FROM %s WHERE %s LIMIT %s,%s;";

    /**
     * 删除模板
     */
    private static final String DELETE_FORMAT = "DELETE FROM %s WHERE %s;";

    /**
     * 数量模板
     */
    private static final String COUNT_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s;";

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
    public String BaseQuery(Object obj,int page,int limit,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT, tableName, s, page, limit);
    }

    /**
     * 构建查询语句（不分页）
     *
     * @param obj
     * @return
     */
    public String BaseQueryNoPage(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT_NOPAGE, tableName, s);
    }
    /**
     * 构建删除语句
     *
     * @param obj
     * @return
     */
    public String BaseDelete(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,false));
        return String.format(DELETE_FORMAT, tableName, s);
    }

    /**
     * 查询符合条件的个数的语句
     *
     * @param obj
     * @return
     */
    public String Count(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(COUNT_FORMAT, tableName, s);
    }

}
