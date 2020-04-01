package com.sws.base.Entity;

import java.util.HashMap;
import java.util.Map;

public class Result extends HashMap {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 0);
    }

    public static Result error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result data(String msg,Object obj,int count) {
        Result r = new Result();
        r.put("msg", msg);
        r.put("data", obj);
        r.put("count", count);
        return r;
    }

    public static Result data(String msg,Object obj) {
        Result r = new Result();
        r.put("msg", msg);
        r.put("data", obj);
        return r;
    }


    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}