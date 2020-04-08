package com.sws.base.util;

//public class InitializeConfig {
//
//    static Yaml yaml = new Yaml();
//
//
//    public static JSONObject getConfig() {
//
//        URL systemResource = InitializeConfig.class.getResource("bootstrap.yml");
//        Map load = null;
//        try {
//            load = yaml.load(new FileInputStream(systemResource.getFile()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return new JSONObject(load);
//    }
//
//}

public class InitializeConfig {

    private volatile static InitializeConfig singleton;

    private InitializeConfig (String a,String b,String c){}

    public static InitializeConfig getSingleton(String a,String b,String c) {
        if (singleton == null) {
            synchronized (InitializeConfig.class) {
                if (singleton == null) {
                    singleton = new InitializeConfig(a,b,c);
                }
            }
        }
        return singleton;
    }
}

