package com.stock.jsp.model;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.stock.model.DataObject;
import com.stock.util.DateService;

public class RatioModel extends BaseObject {

	@Override
	void execute() {
		//System.out.println("RatioMobel.java!!!");
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("192.168.0.199", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("admin");
		if (!db.authenticate("sa", "sa123".toCharArray())) {
			System.out.println("MongoDB auth failed!");
		}

		DB stock = mongoClient.getDB("stock");
		DBCollection chinaunion = stock.getCollection("chinaunion");

		DBCursor cur = chinaunion.find();

		List<Map<String, String>> RatioList = new ArrayList<>();
		try{
		while (cur.hasNext()) {
			DBObject object = cur.next();

			String time = null;
			String ratio = null;
			try {
				time = (String) object.get("doctime");
				ratio = (String) object.get("r0x_ratio");
			} catch (Exception e) {
				continue;
			}
			if (time == null || ratio == null)
				continue;
			Map<String, String> ratioMap = new HashMap<String, String>();
			int index = ratio.indexOf(".");
			if (index != -1)
				ratioMap.put("ratio", ratio.substring(0, index));
			else
				ratioMap.put("ratio", ratio);
			ratioMap.put("time", time);
			RatioList.add(ratioMap);
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Loop Error");
		}
		//System.out.println("RatioMobel.java");
		request.setAttribute("ratio_data", RatioList);
	}

}
