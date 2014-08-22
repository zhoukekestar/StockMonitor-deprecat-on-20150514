package com.stock.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.stock.app.App;
import com.stock.util.HttpReq;

public class DataObject {
	public static final String urlAngel = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/MoneyFlow.ssi_ssfx_flzjtj?daima=sh600050";
	static public DBObject getDbObject()
	{
		DBObject document = new BasicDBObject();
		
		String temp = HttpReq.Get(urlAngel);
		//System.out.println(temp);
		temp = temp.substring(temp.indexOf("{") + 1, temp.indexOf("}"));

		String[] strarray = temp.split(",");
		for (int i = 0; i < strarray.length; i++)
		{
			String strTemp = strarray[i];
			String key = strTemp.substring(0, strTemp.indexOf(":"));
			String val = strTemp.substring(strTemp.indexOf("\"") + 1, strTemp.lastIndexOf("\""));
			document.put(key, val);
		}
		return document;
	}
}
