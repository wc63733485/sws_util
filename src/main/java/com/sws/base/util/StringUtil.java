package com.sws.base.util;

public class StringUtil {

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 是否为空
     * @param string
     * @return
     */
    public static Boolean isEmpty(String string) {
        return string == null || EMPTY_STRING.equals(string.trim());
    }

}
