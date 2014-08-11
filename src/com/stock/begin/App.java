package com.stock.begin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
	private static final String url = "http://vip.stock.finance.sina.com.cn/moneyflow/#!ssfx!sh600050";

	public final static void main(String[] args) throws Exception {
		
		String temp = GetUrl.Get(url);
		System.out.println(temp);
		System.out.println("--------------------------------");
		/*Pattern p = Pattern
				.compile("<div class=\"div_ssfx_right\">.*?<div class=\"clear\"></div>");
		Matcher m = p.matcher(temp);
		while (m.find()) {
			System.out.println(m.group(1));
		}*/
	}

}