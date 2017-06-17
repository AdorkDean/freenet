package com.freenet.tools;

public class StringSub {

	public static String getRequestSub(String urlString,String start,String end){
		int begain = urlString.indexOf(start)+start.length();
		int last = urlString.indexOf(end);
		String content = urlString.substring(begain, last);
		return content;
	}
	
}
