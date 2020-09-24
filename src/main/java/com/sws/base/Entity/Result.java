package com.sws.base.Entity;

import com.alibaba.fastjson.JSONObject;

public class Result extends JSONObject {
    private static final long serialVersionUID = 1L;

    public static Result msg(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result msg(int code, String msg,Object obj) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", obj);
        return r;
    }

    public static Result ok(int code, String msg,Object obj,int count) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", obj);
        r.put("count", count);
        return r;
    }
}
