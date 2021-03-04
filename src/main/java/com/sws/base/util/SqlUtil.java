package com.sws.base.util;

import com.sws.base.annotations.Entity;

import java.util.List;

public class SqlUtil {

    private static final String DESC = "DESC";
    private static final String ASC = "ASC";

    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s );";

    private static final String SELECT_ID_FORMAT = "SELECT * FROM %s WHERE id = %s;";

    private static final String SELECT_SORT_PAGE_FORMAT = "SELECT * FROM %s WHERE %s ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_SORT_FORMAT = "SELECT * FROM %s WHERE %s ORDER BY %s %s;";

    private static final String SELECT_PAGE_FORMAT = "SELECT * FROM %s WHERE %s LIMIT %s,%s;";

    private static final String SELECT_FORMAT = "SELECT * FROM %s WHERE %s;";

    private static final String SELECT_FORMAT_FIELD = "SELECT * FROM %s WHERE %s = %s;";

    private static final String SELECT_ALL_SORT_PAGE_FORMAT = "SELECT * FROM %s ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_ALL_SORT_FORMAT = "SELECT * FROM %s ORDER BY %s %s;";

    private static final String SELECT_ALL_PAGE_FORMAT = "SELECT * FROM %s LIMIT %s,%s;";

    private static final String SELECT_ALL_FORMAT = "SELECT * FROM %s;";

    private static final String DELETE_FORMAT = "DELETE FROM %s WHERE %s;";

    private static final String COUNT_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s;";

    private static final String COUNT_IN_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s IN (%s);";

    private static final String COUNT_ALL_FORMAT = "SELECT COUNT(*) FROM %s;";

    private static final String UPDATE_FORMAT = "UPDATE %s SET %s WHERE %s;";

    private static final String SELECT_IN = "SELECT * FROM %s WHERE %s IN (%s)";

    private static final String SELECT_PAGE_IN = "SELECT * FROM %s WHERE %s IN (%s) LIMIT %s,%s;";

    public static String sort(int i) {
        if (i == 1) {
            return ASC;
        } else {
            return DESC;
        }
    }

    public static String insert(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2Str(FieldUtil.getFields(obj));
        String v = StringUtil.concatCollection2Str(FieldUtil.getFieldsValues(obj));
        return String.format(INSERT_FORMAT, tableName, s, v);
    }

    public static String queryByFiled(String tableName, String columnField, Object queryField) {
        return String.format(SELECT_FORMAT_FIELD, tableName, columnField, queryField);
    }

    public static String query(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, false));
        return String.format(SELECT_FORMAT, tableName, s);
    }

    public static String querySortPage(Object obj, boolean vague, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return String.format(SELECT_ALL_SORT_PAGE_FORMAT, tableName, sort, sort(i), page, limit);
        } else {
            return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s, sort, sort(i), page, limit);
        }
    }

    public static String queryPage(Object obj, boolean vague, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_PAGE_FORMAT, tableName, s, page, limit);
    }

    public static String querySort(Object obj, String sort, int i, boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_SORT_FORMAT, s, tableName, sort, sort(i));
    }

    public static String queryById(Object obj, Integer id) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ID_FORMAT, tableName, id);
    }

    public static String queryAll(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_FORMAT, tableName);
    }

    public static String queryAllPage(Object obj, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_PAGE_FORMAT, tableName, page, limit);
    }

    public static String queryAllSort(Object obj, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_SORT_FORMAT, tableName, sort, sort(i));
    }

    public static String queryAllSortPage(Object obj, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_SORT_PAGE_FORMAT, tableName, sort, sort(i), page, limit);
    }

    public static String queryIn(Object obj, String Field, List<String> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT * FROM " + tableName + " WHERE 1=2";
        }
        String s = StringUtil.concatCollection2Str(array);
        return String.format(SELECT_IN, tableName, Field, s);
    }

    public static String queryInByIntArray(Object obj, String Field, List<Integer> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT * FROM " + tableName + " WHERE 1=2";
        }

        String conn = ",";
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer str : array) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(conn);
            }
            stringBuilder.append(str.toString());
        }

        return String.format(SELECT_IN, tableName, Field, stringBuilder.toString());
    }

    public static String queryInByIntArrayPage(Object obj, String Field, List<Integer> array,int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT * FROM " + tableName + " WHERE 1=2";
        }

        String conn = ",";
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer str : array) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(conn);
            }
            stringBuilder.append(str.toString());
        }

        return String.format(SELECT_PAGE_IN, tableName, Field, stringBuilder.toString(), page, limit);
    }

    public static String queryNoEqSortPage(Object obj, boolean vague, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj, vague));
        return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s, sort, sort(i), page, limit);
    }

    public static String queryNoEqualPage(Object obj, boolean vague, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj, vague));
        return String.format(SELECT_PAGE_FORMAT, tableName, s, page, limit);
    }

    public static String queryNoEqualSort(Object obj, boolean vague, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj, vague));
        return String.format(SELECT_SORT_FORMAT, tableName, s, sort, sort(i));
    }

    public static String queryNoEqual(Object obj, boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj, vague));
        return String.format(SELECT_FORMAT, tableName, s);
    }

    public static String queryOrSortPage(Object obj, boolean vague, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s, sort, sort(i), page, limit);
    }

    public static String queryOrPage(Object obj, boolean vague, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_PAGE_FORMAT, tableName, s, page, limit);
    }

    public static String queryOrSort(Object obj, boolean vague, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_SORT_FORMAT, tableName, s, sort, sort(i));
    }

    public static String queryOr(Object obj, boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_FORMAT, tableName, s);
    }

    public static String orCount(Object obj, boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrOR(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return String.format(COUNT_ALL_FORMAT, tableName);
        }else{
            return String.format(COUNT_FORMAT, tableName, s);
        }
    }

    public static String inCountStr(Object obj,String queryField, List<String> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT COUNT(*) FROM " + tableName + " WHERE 1=2";
        }
        String s = StringUtil.concatCollection2Str(array);
        return String.format(COUNT_IN_FORMAT, tableName, queryField, s);
    }

    public static String inCountInt(Object obj,String queryField,  List<Integer> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "0";
        }

        String conn = ",";
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer str : array) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(conn);
            }
            stringBuilder.append(str.toString());
        }

        return String.format(COUNT_IN_FORMAT, tableName, queryField, stringBuilder.toString());
    }

    public static String andCount(Object obj, boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return String.format(COUNT_ALL_FORMAT, tableName);
        }else{
            return String.format(COUNT_FORMAT, tableName, s);
        }
    }

    public static String countAll(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(COUNT_ALL_FORMAT, tableName);
    }

    public static String delete(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, false));
        return String.format(DELETE_FORMAT, tableName, s);
    }

    public static String update(Object obj1, Object obj2) {
        String tableName = TableUtil.getTableName(obj1);
        String s1 = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj1, false));
        String s2 = StringUtil.concatCollection2Str(FieldUtil.getNotNullFiledString(obj2, false));
        return String.format(UPDATE_FORMAT, tableName, s2, s1);
    }

}
