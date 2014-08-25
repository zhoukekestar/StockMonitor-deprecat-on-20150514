package com.stock.control;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.stock.model.DataObject;
import com.stock.util.DateService;

public class DataCtrl {
	public static void insertCurData() throws UnknownHostException
	{
		/*连接数据库*/
		MongoClient mongoClient = new MongoClient("192.168.0.199", 27017);
		DB db = mongoClient.getDB("admin");
		if (!db.authenticate("sa", "sa123".toCharArray())){
			System.out.println("MongoDB auth failed!");
		}
		
		DB stock = mongoClient.getDB("stock");
		DBCollection chinaunion = stock.getCollection("chinaunion");
		
		
		/*从internet获取数据*/
		DBObject documnet = DataObject.getDbObject();
		
		Date date = DateService.getInstance().getInternetDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
		documnet.put("doctime", format.format(date));
		documnet.put("doctype", "2");
        chinaunion.insert(documnet);
        System.out.println(DateService.getInstance().getInternetDate().toString() + " insert one document.");
	}
}
