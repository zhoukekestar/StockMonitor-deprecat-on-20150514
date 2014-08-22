package com.stock.control;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.stock.model.DataObject;
import com.stock.util.DateService;

public class DataCtrl {
	public static void insertCurData() throws UnknownHostException
	{
		MongoClient mongoClient = new MongoClient("192.168.0.199", 27017);
		DB db = mongoClient.getDB("admin");
		if (!db.authenticate("sa", "sa123".toCharArray())){
			System.out.println("MongoDB auth failed!");
		}
		
		DB stock = mongoClient.getDB("stock");
		DBCollection chinaunion = stock.getCollection("chinaunion");
		
		DBObject documnet = DataObject.getDbObject();
		documnet.put("time", DateService.getInstance().getInternetDate().toString());
        chinaunion.insert(documnet);
        System.out.println(DateService.getInstance().getInternetDate().toString() + " insert one document.");
	}
}
