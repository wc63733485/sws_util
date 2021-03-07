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

    private static final String SELECT_FORMAT = "SELECT * FROM %s WHERE %s;";

    private static final String SELECT_ONE_FORMAT = "SELECT * FROM %s WHERE %s LIMIT 0,1;";

    private static final String SELECT_FORMAT_FIELD = "SELECT * FROM %s WHERE %s = %s;";

    private static final String SELECT_ALL_SORT_PAGE_FORMAT = "SELECT * FROM %s ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_ALL_SORT_FORMAT = "SELECT * FROM %s ORDER BY %s %s;";

    private static final String SELECT_ALL_FORMAT = "SELECT * FROM %s;";

    private static final String DELETE_FORMAT = "DELETE FROM %s WHERE %s;";

    private static final String COUNT_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s;";

    private static final String COUNT_IN_FORMAT = "SELECT COUNT(*) FROM %s WHERE %s IN (%s);";

    private static final String COUNT_ALL_FORMAT = "SELECT COUNT(*) FROM %s;";

    private static final String UPDATE_FORMAT = "UPDATE %s SET %s WHERE %s;";

    private static final String SELECT_IN = "SELECT * FROM %s WHERE %s IN (%s)";

    private static final String SELECT_SORT_PAGE_IN = "SELECT * FROM %s WHERE %s IN (%s) ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_SORT_IN = "SELECT * FROM %s WHERE %s IN (%s) ORDER BY %s %s;";

    private static final String SELECT_CONDITION_SORT_PAGE = "SELECT * FROM %s WHERE %s and %s IN (%s) ORDER BY %s %s LIMIT %s,%s;";

    private static final String SELECT_CONDITION_SORT = "SELECT * FROM %s WHERE %s and %s IN (%s) ORDER BY %s %s;";

    private static final String SELECT_CONDITION = "SELECT * FROM %s WHERE %s and %s IN (%s);";

    private static final String COUNT_CONDITION = "SELECT COUNT(*) FROM %s WHERE %s and %s IN (%s);";

    public static String insert(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2Str(FieldUtil.getFields(obj));
        String v = StringUtil.concatCollection2Str(FieldUtil.getFieldsValues(obj));
        return String.format(INSERT_FORMAT, tableName, s, v);
    }

    public static String queryByFiled(Object obj, String columnField, Object queryField) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_FORMAT_FIELD, tableName, columnField, queryField);
    }

    public static String query(Object obj,boolean vague) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_FORMAT, tableName, s);
    }

    public static String querySort(Object obj,boolean vague, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        return String.format(SELECT_SORT_FORMAT, s, tableName, sort, sort(i));
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

//    public static String queryInByString(Object obj, String Field, List<String> array) {
//        String tableName = TableUtil.getTableName(obj);
//        if (array.size() == 0) {
//            return "SELECT * FROM " + tableName + " WHERE 1=2";
//        }
//        String s = StringUtil.concatCollection2Str(array);
//        return String.format(SELECT_IN, tableName, Field, s);
//    }

    public static String queryIn(Object obj, String Field, List<Integer> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT * FROM " + tableName + " WHERE 1=2";
        }

        String string = listToString(array);

        return String.format(SELECT_IN, tableName, Field, string);
    }

    public static String queryInBySort(Object obj, String Field, List<Integer> array, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT * FROM " + tableName + " WHERE 1=2";
        }

        String string = listToString(array);

        return String.format(SELECT_SORT_IN, tableName, Field, string,sort, sort(i));
    }

    public static String queryInBySortPage(Object obj, String Field, List<Integer> array, String sort, int i,int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT * FROM " + tableName + " WHERE 1=2";
        }

        String string = listToString(array);

        return String.format(SELECT_SORT_PAGE_IN, tableName, Field, string,sort, sort(i), page, limit);
    }

    public static String queryInAndCondition(Object obj, boolean vague,String Field, List<Integer> array) {
        String tableName = TableUtil.getTableName(obj);

        if (array.size() == 0) {
            return query(obj,vague);
        }

        String string = listToString(array);

        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return queryIn(obj,Field,array);
        }
        return String.format(SELECT_CONDITION, tableName,s, Field, string);
    }

    public static String queryInAndConditionSort(Object obj, boolean vague,String Field, List<Integer> array, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);

        if (array.size() == 0) {
            return querySort(obj,vague,sort,i);
        }

        String string = listToString(array);

        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return queryInBySort(obj,Field,array,sort,i);
        }
        return String.format(SELECT_CONDITION_SORT_PAGE, tableName,s, Field, string,sort, sort(i));
    }

    public static String queryInAndConditionSortPage(Object obj, boolean vague,String Field, List<Integer> array, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);

        if (array.size() == 0) {
            return querySortPage(obj,vague,sort,i,page,limit);
        }

        String string = listToString(array);

        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return queryInBySortPage(obj,Field,array,sort,i,page,limit);
        }
        return String.format(SELECT_CONDITION_SORT_PAGE, tableName,s, Field, string,sort, sort(i),page, limit);
    }

    public static String queryOne(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, false));
        return String.format(SELECT_ONE_FORMAT, tableName,s);
    }

    public static String queryById(Object obj, Integer id) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ID_FORMAT, tableName, id);
    }

    public static String queryAll(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_FORMAT, tableName);
    }

    public static String queryAllSort(Object obj, String sort, int i) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_SORT_FORMAT, tableName, sort, sort(i));
    }

    public static String queryAllSortPage(Object obj, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        return String.format(SELECT_ALL_SORT_PAGE_FORMAT, tableName, sort, sort(i), page, limit);
    }

    public static String queryNoEqSortPage(Object obj, boolean vague, String sort, int i, int page, int limit) {
        String tableName = TableUtil.getTableName(obj);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledStringNoEqual(obj, vague));
        return String.format(SELECT_SORT_PAGE_FORMAT, tableName, s, sort, sort(i), page, limit);
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

    public static String inCountByString(Object obj,String queryField, List<String> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT COUNT(*) FROM " + tableName + " WHERE 1=2";
        }
        String s = StringUtil.concatCollection2Str(array);
        return String.format(COUNT_IN_FORMAT, tableName, queryField, s);
    }

    public static String inCountByInt(Object obj,String queryField,  List<Integer> array) {
        String tableName = TableUtil.getTableName(obj);
        if (array.size() == 0) {
            return "SELECT COUNT(*) FROM " + tableName + " WHERE 1=2";
        }

        String string = listToString(array);

        return String.format(COUNT_IN_FORMAT, tableName, queryField, string);
    }

    public static String countInAndCondition(Object obj, boolean vague,String field, List<Integer> array) {
        String tableName = TableUtil.getTableName(obj);

        if (array.size() == 0) {
            return andCount(obj,vague);
        }

        String string = listToString(array);
        String s = StringUtil.concatCollection2StrAND(FieldUtil.getNotNullFiledString(obj, vague));
        if (s.equals("")) {
            return inCountByInt(obj,field,array);
        }
        return String.format(COUNT_CONDITION, tableName, s, field, string);
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

    public static String listToString(List<Integer> array){
        String conn = ",";
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer str : array) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(conn);
            }
            stringBuilder.append(str.toString());
        }
        return stringBuilder.toString();
    }

    public static String sort(int i) {
        if (i == 1) {
            return ASC;
        } else {
            return DESC;
        }
    }
}
