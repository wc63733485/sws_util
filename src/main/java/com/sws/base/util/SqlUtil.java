package com.sws.base.util;

import com.sws.base.annotations.Entity;

import java.util.List;

public class SqlUtil {

    private final String DESC = "DESC";
    private final String ASC = "ASC";

    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s );";

    private static final String SELECT_ID_FORMAT = "SELECT * FROM %s WHERE id = %s;";

    private static final String SELECT_SORT_PAGE_FORMAT = "SELECT * FROM %s WHERE %s ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_SORT_FORMAT = "SELECT * FROM %s WHERE %s ORDER BY %s %s;";

    private static final String SELECT_PAGE_FORMAT = "SELECT * FROM %s WHERE %s LIMIT %s,%s;";

    private static final String SELECT_FORMAT = "SELECT * FROM %s WHERE %s;";

    private static final String SELECT_ALL_SORT_PAGE_FORMAT = "SELECT * FROM %s ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_ALL_SORT_FORMAT = "SELECT * FROM %s ORDER BY %s %s;";

    private static final String SELECT_ALL_PAGE_FORMAT = "SELECT * FROM %s LIMIT %s,%s;";

    private static final String SELECT_ALL_FORMAT = "SELECT * FROM %s;";

    private static final String DELETE_FORMAT = "DELETE FROM %s WHERE %s;";

    private static final String COUNT_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s;";

    private static final String UPDATE_FORMAT = "UPDATE %s SET %s WHERE %s;";

    private static final String SELECT_IN = "SELECT * FROM %s WHERE %s in (%s)";

    public String sort(int i) {
        if (i==1){
            return ASC;
        }else{
            return DESC;
        }
    }

    public String insert(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2Str(FieldUtil.getFields(obj));
        String v = StringUtil.concatCollection2Str(FieldUtil.getFieldsValues(obj));
        return String.format(INSERT_FORMAT, tableName, s, v);
    }

    public String query(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,false));
        return String.format(SELECT_FORMAT, tableName,s);
    }
    public String queryPageSort(Object obj,boolean vague,int page,int limit,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s,sort,sort(i), page, limit);
    }
    public String queryPage(Object obj,boolean vague,int page,int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_PAGE_FORMAT, tableName,s, page, limit);
    }
    public String querySort(Object obj,String sort,int i,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_SORT_FORMAT,s, tableName, sort, sort(i));
    }

    public String queryById(String name,Integer id) {
        return String.format(SELECT_ID_FORMAT, name, id);
    }

    public String queryAll(String tableName) {
        return String.format(SELECT_ALL_FORMAT, tableName);
    }
    public String queryAllPage(String tableName,int page,int limit) {
        return String.format(SELECT_ALL_PAGE_FORMAT, tableName, page,limit);
    }
    public String queryAllSort(String tableName,String sort,int i) {
        return String.format(SELECT_ALL_SORT_FORMAT, tableName,sort,sort(i));
    }
    public String queryAllSortPage(String tableName,String sort,int i,int page,int limit) {
        return String.format(SELECT_ALL_SORT_PAGE_FORMAT, tableName,sort,sort(i),page,limit);
    }

    public String queryIn(Object obj, String Field,List array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size()==0){
            return "SELECT * FROM"+tableName+"WHERE 1=2";
        }
        String s = StringUtil.concatCollection2Str(array);
        return String.format(SELECT_IN, tableName,Field, s);
    }

    public String queryNoEqSortPage(Object obj,boolean vague,int page,int limit,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj,vague));
        return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s,sort,sort(i),page,limit);
    }
    public String queryNoEqualPage(Object obj,boolean vague,int page,int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj,vague));
        return String.format(SELECT_PAGE_FORMAT, tableName, s,page,limit);
    }
    public String queryNoEqualSort(Object obj,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj,vague));
        return String.format(SELECT_SORT_FORMAT, tableName, s,sort,sort(i));
    }
    public String queryNoEqual(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj,vague));
        return String.format(SELECT_FORMAT, tableName, s);
    }

    public String queryOrSortPage(Object obj,boolean vague,int page,int limit,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s,sort,sort(i),page, limit);
    }
    public String queryOrPage(Object obj,boolean vague,int page,int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_PAGE_FORMAT, tableName, s, page, limit);
    }
    public String queryOrSort(Object obj,boolean vague,String sort,int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_SORT_FORMAT, tableName, s,sort,sort(i));
    }
    public String queryOr(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(SELECT_FORMAT, tableName, s);
    }

    public String orCount(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(COUNT_FORMAT, tableName, s);
    }
    public String andCount(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,vague));
        return String.format(COUNT_FORMAT, tableName, s);
    }

    public String delete(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj,false));
        return String.format(DELETE_FORMAT, tableName, s);
    }

    public String update(Object obj1,Object obj2) {
        String tableName = TableUtil.getTableName(obj1);
        String s1 = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj1,false));
        String s2 = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj2,false));
        return String.format(UPDATE_FORMAT, tableName, s2, s1);
    }

}
