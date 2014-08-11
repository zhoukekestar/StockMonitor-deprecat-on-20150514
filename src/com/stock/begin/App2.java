package com.stock.begin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App2 {
public static void main(String[] args) {
	String testStr = "12315<Test>show me</text>";
	Pattern p = Pattern.compile("<Test>(.*)</text>");
	Matcher m = p.matcher(testStr);
	while(m.find()){
	System.out.println(m.group(1));
	}
	System.out.println("over");
	//<span style="white-space: pre;">	</span>
}
}
