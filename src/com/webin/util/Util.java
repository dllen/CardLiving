// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Util.java

package com.webin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Util
{
	public static int codeId = 1;
	public static int eatPages = 0;
	public static int playPages = 0;
	public static int hotelPages = 0;
	public static int lifePages = 0;
	public static int westPages = 0;
	public static int otherPages = 0;
	public Util()
	{
	}
	//
	public static List<String> getImageList(String in){
		List<String> imageList = new ArrayList<String>();
		StringBuffer sbf = new StringBuffer();
		for(int i = 0; in != null && i < in.length(); i++){
			char c = in.charAt(i);
			if (c == '-'){
				System.out.println(sbf.toString()+"--------------");
				imageList.add(sbf.toString());
				sbf = new StringBuffer();
			}
			else{				
				sbf.append(c);
			}
		}
		return imageList;
	}

	public static void toNextString(String in,StringBuffer sbf){
		for(int i = 0; in != null && i < in.length(); i++){
			char c = in.charAt(i);
			if (c == ' ')
				sbf.append("&nbsp;");
			else
			if (c == '-'){
				sbf.append("\n");
			}
			else
				sbf.append(c);
		}
	}
	public static String toMyString(String in){
		StringBuffer sbf = new StringBuffer();
		for(int i = 0; in != null && i < in.length(); i++){
			char c = in.charAt(i);
			if (c == '-'){
				System.out.println("+++++++++++++++++");
				sbf.append("\n");
			}
			else
				sbf.append(c);
		}
		return sbf.toString();
	}
	public static String toHTMLString(String in)
	{
		StringBuffer out = new StringBuffer();
		for (int i = 0; in != null && i < in.length(); i++)
		{
			char c = in.charAt(i);
//			if (c == '\'')
//				out.append("&#039;");
//			else
//			if (c == '"')
//				out.append("&#034;");
//			else
//			if (c == '<')
//				out.append("&lt;");
//			else
//			if (c == '>')
//				out.append("&gt;");
//			else
//			if (c == '&')
//				out.append("&amp;");
//			else
//			if (c == ' ')
//				out.append("&nbsp;");
//			else
			if (c == '-')
				out.append("<br>");
			else
				out.append(c);
		}

		return out.toString();
	}
	//随机产生相应页数进行查询
	public static int setPageByCategory(int category){
		int page = 1;
		Random r = new Random();
		if(category==1){
			if(Util.eatPages==0)return 1;
			return r.nextInt(Util.eatPages);
		}else if(category==2){
			if(Util.playPages==0)return 1;
			return r.nextInt(Util.playPages);
		}else if(category==3){
			if(Util.hotelPages==0)return 1;
			return r.nextInt(Util.hotelPages);
		}else if(category==4){
			if(Util.lifePages==0)return 1;
			return r.nextInt(Util.lifePages);
		}else if(category==5){
			if(Util.westPages==0)return 1;
			return r.nextInt(Util.westPages);
		}else if(category==6){
			if(Util.otherPages==0)return 1;
			return r.nextInt(Util.otherPages);
		}
		return page;
	}
}
