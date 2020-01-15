package com.sws.base.util;

public class LogMessage {
    private static int logLevel;

    public static void info(String message){
        System.out.println(message);
    }

    public static void debug(String message){
        System.out.println(message);
    }

    public static void error(String message){
        System.out.println(message);
    }

    public static void warn(String message){
        System.out.println(message);
    }

    public static int getLogLevel(){
        logLevel = Integer.parseInt((String) YamlUtil.getValue("log.level"));
        return logLevel;
    }
}
