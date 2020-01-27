package com.sws.base.util;

import org.yaml.snakeyaml.Yaml;
import sun.net.ResourceManager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YamlUtil {

    private static String bootstrap_file = "bootstrap.yml";
    private static String application_file = "application.yml";
    private static Map<String,Object> result = new HashMap<String,Object>();
    /**
     * 根据文件名获取yml的文件内容
     * @return
     */
    public static Map<String,Object> getYmlByFileName(String file){
        result = new HashMap<String,Object>();
        if(file == null)
            file = bootstrap_file;
        InputStream in = ResourceManager.class.getClassLoader().getResourceAsStream(file);
        Yaml props = new Yaml();
        Object obj = props.loadAs(in,Map.class);
        Map<String,Object> param = (Map<String, Object>) obj;
        for(Map.Entry<String,Object> entry:param.entrySet()){
            String key = entry.getKey();
            Object val = entry.getValue();

            if(val instanceof Map){
                forEachYaml(key,(Map<String, Object>) val);
            }else{
                result.put(key,val.toString());
            }
        }
        return result;
    }
    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public static Object getValue(String key){
        Map<String,Object> map = getYmlByFileName(application_file);
        if(map==null)return null;
        return map.get(key);
    }
    /**
     * 遍历yml文件，获取map集合
     * @param key_str
     * @param obj
     * @return
     */
    public static Map<String,Object> forEachYaml(String key_str,Map<String, Object> obj){
        for(Map.Entry<String,Object> entry:obj.entrySet()){
            String key = entry.getKey();
            Object val = entry.getValue();
            String str_new = "";
            if(null!=key_str){
                str_new = key_str+ "."+key;
            }else{
                str_new = key;
            }
            if(val instanceof Map){
                forEachYaml(str_new,(Map<String, Object>) val);
            }else{
                result.put(str_new,val.toString());
            }
        }
        return result;
    }

}
