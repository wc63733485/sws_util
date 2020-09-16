package com.sws.base.util;

import com.sws.base.annotations.Entity;

import java.util.List;

public class SqlUtil {

    private final String DESC = "DESC";
    private final String ASC = "ASC";

    /**
     * 插入模板
     */
    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s );";

    /**
     * 查询模板
     */
    private static final String SELECT_ONE_FORMAT = "SELECT * FROM %s WHERE %s;";

    /**
     * 查询模板
     */
    private static final String SELECT_FORMAT = "SELECT * FROM %s WHERE %s ORDER BY %s %s LIMIT %s,%s;";


    /**
     * 查询模板
     */
    private static final String SELECTID_FORMAT = "SELECT * FROM %s WHERE id = %s;";

    /**
     * 查询模板
     */
    private static final String SELECTALL_FORMAT = "SELECT * FROM %s ORDER BY %s %s;";

    /**
     * 查询模板不分页
     */
    private static final String SELECT_FORMAT_NOPAGE = "SELECT * FROM %s WHERE %s ORDER BY %s %s;";

    /**
     * 删除模板
     */
    private static final String DELETE_FORMAT = "DELETE FROM %s WHERE %s;";

    /**
     * 数量模板
     */
    private static final String COUNT_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s;";

    /**
     * 更新模板
     */
    private static final String UPDATE_FORMAT = "UPDATE %s SET %s WHERE %s;";

    /**
     * 包含查询模板
     */
    private static final String SELECT_IN = "select * from %s where id in (%s)";

    /**
     * 构建插入语句
     *
     * @param i
     * @return
     */
    public String sort(int i) {
        if (i==1){
            return ASC;
        }else{
            return DESC;
        }
    }


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
    public String BaseQuery(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,false));
        return String.format(SELECT_ONE_FORMAT, tableName,s);
    }

    /**
     * 构建查询语句
     *
     * @param obj
     * @return
     */
    public String BaseQuery(Object obj,int page,int limit,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT, tableName, s,sort,sort(i), page, limit);
    }

    /**
     * 构建id查询语句
     * @return
     */
    public String BaseIdQuery(String name,Integer id) {
        return String.format(SELECTID_FORMAT, name, id);
    }

    /**
     * 构建查询语句（不分页）
     *
     * @param obj
     * @return
     */
    public String BaseQueryNoPage(Object obj,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT_NOPAGE, tableName, s,sort,sort(i));
    }

    /**
     * 构建查询语句（不分页）
     *
     * @param obj
     * @return
     */
    public String BaseQueryIn(Object obj, List array) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2Str(array);
        if (array.size()==0){
            return "select * from device_unit where id = 0";
        }
        return String.format(SELECT_IN, tableName, s);
    }

    /**
     * 构建不等查询语句（不分页）
     *
     * @param obj
     * @return
     */
    public String BaseQueryNoEqualNoPage(Object obj,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj,vague));
        return String.format(SELECT_FORMAT_NOPAGE, tableName, s,sort,sort(i));
    }

    /**
     * 构建查询语句
     *
     * @param obj
     * @return
     */
    public String BaseQueryOr(Object obj,int page,int limit,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT, tableName, s,sort,sort(i), page, limit);
    }

    /**
     * 构建查询语句
     *
     * @param obj
     * @return
     */
    public String OrCount(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(COUNT_FORMAT, tableName, s);
    }

    /**
     * 查询符合条件的个数的语句
     *
     * @param obj
     * @return
     */
    public String AndCount(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(COUNT_FORMAT, tableName, s);
    }

    /**
     * 构建查询语句（不分页）
     *
     * @param obj
     * @return
     */
    public String BaseQueryNoPageOr(Object obj,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT_NOPAGE, tableName, s,sort,sort(i));
    }



    /**
     * 构建查询语句（不分页）
     *
     * @param obj
     * @return
     */
    public String BaseQueryAll(Object obj,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECTALL_FORMAT, tableName,sort,sort(i));
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
     * @param obj1 obj2
     * @return
     */
    public String Update(Object obj1,Object obj2) {
        String tableName = TableUtil.getTableName(obj1);
        String s1 = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj1,false));
        String s2 = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj2,false));
        return String.format(UPDATE_FORMAT, tableName, s2, s1);
    }

}
