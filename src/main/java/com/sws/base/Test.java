package com.sws.base;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;


public class Test {

    public static void main(String[] args) {
        for (String str:doA()) {
            System.out.println(str);
        }
    }

    public static ArrayList<String> doA(){
        System.out.println("asd");
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("12");
        list.add("1");
        list.add("1234");
        return list;
    }
}
