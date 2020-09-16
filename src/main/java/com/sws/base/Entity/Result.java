package com.sws.base.Entity;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Result extends JSONObject {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 200);
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
    public static Result ok() {
        return new Result();
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg,Object obj) {
        Result r = new Result();
        r.put("msg", msg);
        r.put("data", obj);
        return r;
    }

    public static Result ok(String msg,Object obj,int count) {
        Result r = new Result();
        r.put("msg", msg);
        r.put("data", obj);
        r.put("count", count);
        return r;
    }
}
