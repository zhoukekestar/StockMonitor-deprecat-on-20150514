package com.stock.jsp.model;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
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
		// System.out.println("RatioMobel.java!!!");

		DBCollection chinaunion = connectTo("stock", "chinaunion");

		DBCursor cur = chinaunion
				.find(new BasicDBObject().append("doctype", "2"))
				.sort(new BasicDBObject().append("doctime", -1)).skip(0)
				.limit(200);

		/* colname & alias */
		String[][] colls = { 
				{ "doctime", "time" }, 
				{ "r0x_ratio", "ratio" },
				{"trade", "trade"}
				};

		request.setAttribute("ratio_data", getData(colls, cur));
	}

	@SuppressWarnings("deprecation")
	private DBCollection connectTo(String DB, String col) {
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

		db = mongoClient.getDB(DB);
		DBCollection dbcol = db.getCollection(col);
		return dbcol;
	}

	private List<Map<String, String>> getData(String[][] cols, DBCursor cur) {
		List<Map<String, String>> list = new ArrayList<>();

		while (cur.hasNext()) {

			DBObject object = cur.next();
			Map<String, String> map = new HashMap<>();
			for (int i = 0; i < cols.length; i++) {
				String temp = null;
				try {
					temp = (String) object.get(cols[i][0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (null == temp || temp.trim().length() <= 0)
					temp = "";
				

				map.put(cols[i][1], specialDeal(cols[i][1], temp));
			}
			list.add(map);
		}

		return list;
	}
	
	private String specialDeal(String flag, String source)
	{
		if (flag.equals("ratio"))
		{
			int index = source.indexOf(".");
			if (index != -1)
				source = source.substring(0, index);
		}
		else if (flag.equals("trade"))
		{
			int index = source.indexOf(".");
			if (index != -1)
				source = source.substring(0, index + 3);
		}
		return source;
	}

}
