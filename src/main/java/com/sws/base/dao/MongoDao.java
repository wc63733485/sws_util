package com.sws.base.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;



/**
 * @author wxc
 * @version Beta1.0
 */
@Repository
public class MongoDao {

    @Autowired
    public MongoTemplate mongoTemplate;

    public boolean save(JSONObject json, String collectionName) {

        JSONObject r = mongoTemplate.save(json,collectionName);
        System.out.println(r.toString());
        return true;
    }

}
