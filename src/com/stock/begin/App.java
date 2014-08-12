package com.stock.begin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stock.util.HttpReq;

public class App {
	public static final String url = "http://vip.stock.finance.sina.com.cn/moneyflow/#!ssfx!sh600050";
	public static final String urlAngel = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/MoneyFlow.ssi_ssfx_flzjtj?daima=sh600050";
	public final static void main(String[] args) throws Exception {
		
//		String temp = HttpReq.Get(url);
//		System.out.println(temp);
//		System.out.println("--------------------------------");
//		Pattern p = Pattern
//				.compile("<div class=\"div_ssfx_right\">.*?<div class=\"clear\"></div>");
//		Matcher m = p.matcher(temp);
//		while (m.find()) {
//			System.out.println(m.group(1));
//		}
//		
		String temp = HttpReq.Get(urlAngel);
		System.out.println(temp);
		
	}

}