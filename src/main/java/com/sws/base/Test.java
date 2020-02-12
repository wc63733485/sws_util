package com.sws.base;

import com.sws.base.Entity.PumpHouseEntity;
import com.sws.base.dao.BaseDao;
import com.sws.base.service.BaseService;
import com.sws.base.service.impl.BaseServiceImpl;
import com.sws.base.util.LogMessage;

import java.util.List;

public class Test {

//    public static void main(String[] args) throws Exception {
//        BaseService bs = new BaseServiceImpl();
//
//        PumpHouseEntity pm = new PumpHouseEntity();
//        pm.setCode("hs2020001");
//        //1. 查询所有泵房信息
//        List<Object> code = bs.findAllByPage(pm, 0, 2, "code", -1);
//        for (Object s : code) {
//            System.out.println(s.toString());
//        }
//    }

    public static void main(String[] args) throws Exception {
        BaseDao bd = new BaseDao();

        PumpHouseEntity pm = new PumpHouseEntity();
        pm.setCode("HS");
        //1. 查询所有泵房信息
        List<Object> objects = bd.queryByPage(pm, 0, 10,true);

        System.out.println(objects.size());

    }
}
