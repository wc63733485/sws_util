//package com.sws.base.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.sws.base.service.BaseService;
//import org.yaml.snakeyaml.Yaml;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Date;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class InitializeConfig {
//
//
//    static Yaml yaml = new Yaml();
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
////        JSONObject jso = new JSONObject(load);
//
////        try {
////            URL url = new URL(jso.getJSONObject("config").getString("url"));
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setDoInput(true);
////            conn.connect();
////            InputStream is = conn.getInputStream();
////            load = yaml.load(is);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//
//        return new JSONObject(load);
//    }
//
//}
//
