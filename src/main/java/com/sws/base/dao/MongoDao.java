//package com.sws.base.dao;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.mongodb.*;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.model.Filters;
//import com.sws.base.util.JavaBeanUtil;
//import org.bson.*;
//import org.bson.conversions.Bson;
//import org.bson.types.ObjectId;
//import org.springframework.stereotype.Repository;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * @author wxc
// * @version Beta2.0
// */
//@Repository
//public class MongoDao {
//
//    private String dbName = "device";
//
//    private String[] uri = {"39.96.74.32:27837"};
//
//    private String username = "root";
//
//    private String password = "ASDzxc1993";
//
//    public void setMongoClient(MongoClient mongoClient) {
//        this.mongoClient = mongoClient;
//    }
//
//    private MongoClient mongoClient = null;
//
//    public MongoDao() {
//        List<ServerAddress> listHost = getServerAddressList();
//        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
//        // options.autoConnectRetry(true);// 自动重连true
//        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
//        options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
//        options.connectTimeout(15000);// 连接超时，推荐>3000毫秒
//        options.maxWaitTime(5000); //
//        options.socketTimeout(0);// 套接字超时时间，0无限制
//        options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
//        options.writeConcern(WriteConcern.SAFE);//
//        MongoClientOptions build = options.build();
//        MongoCredential mongoCredential = MongoCredential.createCredential(username, "admin", password.toCharArray());
//        MongoClient mongoClient = new MongoClient(listHost, mongoCredential, build);
//        this.mongoClient = mongoClient;
//    }
//
//    public List<ServerAddress> getServerAddressList() {
//
//        List<ServerAddress> serverAddressList = new ArrayList<>();
//        ServerAddress serverAddress = null;
//        for (Object object : uri) {
//            serverAddress = new ServerAddress(object.toString());
//            serverAddressList.add(serverAddress);
//        }
//
//        return serverAddressList;
//    }
//
//    /**
//     * 获取collection对象 - 指定Collection
//     *
//     * @param collName
//     * @return
//     */
//    private MongoCollection<Document> getCollection(String dataName, String collName) {
//        if (null == collName || "".equals(collName)) {
//            return null;
//        }
//        if (null == dataName || "".equals(dataName)) {
//            return null;
//        }
//        MongoCollection<Document> collection = this.mongoClient.getDatabase(dataName).getCollection(collName);
//        return collection;
//    }
//
//
//    public <T> T findById(Class<T> obj, String id) {
//
//        MongoCollection<Document> coll = getCollection(dbName, obj.getSimpleName());
//
//        Document myDoc = null;
//
//        try {
//
//            ObjectId _idobj = new ObjectId(id);
//
//            myDoc = coll.find(Filters.eq("_id", _idobj)).first();
//
//            if (myDoc == null) {
//
//                return obj.newInstance();
//
//            }
//        } catch (IllegalArgumentException e) {
//
//            throw new IllegalArgumentException(e);
//
//        } catch (IllegalAccessException e) {
//
//            e.printStackTrace();
//
//        } catch (InstantiationException e) {
//
//            e.printStackTrace();
//        }
//        T t = (T) JavaBeanUtil.mapToObject(myDoc, obj);
//        return t;
//    }
//
//
//    public JSONObject find(String dbName,String collectionName,JSONObject jso,JSONObject jsosort,int page,int limit) {
//
//        MongoCollection<Document> coll = getCollection(dbName,collectionName);
//
//        Document myDoc = null;
//
//        try {
//
//            BasicDBObject find = new BasicDBObject();
//            BasicDBObject sort = new BasicDBObject();
//            sort.putAll(jsosort);
//
//            find.putAll(jso);
//
//            FindIterable<Document> documents = coll.find(find).sort(sort).skip((page-1)*limit).limit(limit);
//
//            if (documents == null) {
//
//                return new JSONObject();
//
//            }
//            return JSON.parseObject(documents.toString());
//        } catch (IllegalArgumentException e) {
//
//            throw new IllegalArgumentException(e);
//
//        }
//
//    }
//
//
//    public JSONObject findOneByNew(JSONObject obj, String collectionName) {
//
//        MongoCollection<Document> coll = getCollection(dbName, collectionName);
//
//        BasicDBObject bson = new BasicDBObject();
//
//        bson.putAll(obj);
//
//        Document doc = new Document();
//
//        doc.put("_id", -1);
//
//        FindIterable<Document> documents = coll.find(bson).sort(doc).limit(1);
//
//        Document first = documents.first();
//        JSONObject jsonObject = new JSONObject();
//        if (first!=null) {
//            jsonObject = JSONObject.parseObject(first.toJson());
//        }
//        return jsonObject;
//    }
//
//    public JSONObject findRowByField(String field, String collectionName, int limit) {
//
//        MongoCollection<Document> coll = getCollection(dbName, collectionName);
//
//        BasicDBObject bson = new BasicDBObject();
//
//        Document doc = new Document();
//        doc.put("time", -1);
//        FindIterable<Document> limit1 = coll.find(bson).sort(doc).limit(limit);
//        MongoCursor<Document> iterator = limit1.iterator();
//        JSONObject r = new JSONObject();
//        JSONArray arrayData = new JSONArray();
//        JSONArray arrayTime = new JSONArray();
//
//        while (iterator.hasNext()) {
//            Document next = iterator.next();
//            arrayTime.add(next.get("time"));
//            arrayData.add(next.get(field));
//        }
//        r.put("time", arrayTime);
//        r.put("data", arrayData);
//        return r;
//    }
//
//    public JSONObject findRowByField(String field, String collectionName, long startTime) {
//
//        MongoCollection<Document> coll = getCollection(dbName, collectionName);
//
//        BasicDBObject bson = new BasicDBObject();
//        BasicDBObject gte = new BasicDBObject();
//        gte.put("$gt", startTime);
//        bson.put("time", gte);
//
//        Document doc = new Document();
//        doc.put("time", -1);
//
//        FindIterable<Document> limit1 = coll.find(bson).sort(doc);
//        MongoCursor<Document> iterator = limit1.iterator();
//        JSONObject r = new JSONObject();
//        JSONArray arrayData = new JSONArray();
//        JSONArray arrayTime = new JSONArray();
//        while (iterator.hasNext()) {
//            Document next = iterator.next();
//            arrayTime.add(next.get("time"));
//            arrayData.add(next.get(field));
//        }
//        r.put("time", arrayTime);
//        r.put("data", arrayData);
//        return r;
//    }
//
//    public Document findById(String collectionName, String id) {
//
//        MongoCollection<Document> coll = getCollection(dbName, collectionName);
//
//        Document myDoc = null;
//
//        try {
//
//            ObjectId _idobj = new ObjectId(id);
//
//            myDoc = coll.find(Filters.eq("_id", _idobj)).first();
//
//            if (myDoc == null) {
//
//                return new Document();
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return myDoc;
//
//    }
//
//
//    public boolean insert(Object o, String collectionName) {
//        try {
//            MongoCollection<Document> coll = getCollection(dbName, collectionName);
//
//            Document d = Document.parse(o.toString());
//
//            coll.insertOne(d);
//
//            return true;
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
//
//    //插入默认集合名为类名
//    public boolean insert(Object o) {
//        try {
//            MongoCollection<Document> coll = this.getCollection(dbName, o.getClass().getSimpleName());
//
//            Document d = Document.parse(o.toString());
//
//            coll.insertOne(d);
//            return true;
//
//        } catch (Exception e) {
//
//            return false;
//        }
//    }
//
//}
