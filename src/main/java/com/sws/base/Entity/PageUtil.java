package com.sws.base.Entity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil extends HashMap {

    private static final long serialVersionUID = 1L;

//    private static final BaseDao bd = new BaseDao();

    public List<?> data;

    public int count;

    public PageUtil(List<?> data, int count) {
        this.data = data;
        this.count = count;
    }


    public List<?> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

}
