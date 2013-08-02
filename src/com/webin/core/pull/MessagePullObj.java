package com.webin.core.pull;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.webin.core.ErrorException;
//用来解析传输过来xml
public class MessagePullObj extends DefaultHandler {
	private HashMap<String, String> mHashMap = new HashMap<String, String>();
	private SAXParserFactory mFactory = SAXParserFactory.newInstance();
	private InputStream mContext;
	private SAXParser mParser;
	private String mXmlKey;

	public MessagePullObj(){
	}
	
	public void handleInputStream(InputStream inputStream){
//		System.out.println("handleInputStream");
		mContext = inputStream;
		try {
			System.out.println("开始解析");
			mParser = mFactory.newSAXParser();
			mParser.parse(mContext, this);
		} catch (Exception e) {
			System.out.println("解析错误？？");
			ErrorException.SAXParser(e);
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (!"xml".equals(qName)) {
			mXmlKey = qName;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (mXmlKey != null) {
			String mXmlValue = new String(ch, start, length);
			if (!mHashMap.containsKey(mXmlKey)) {
				mHashMap.put(mXmlKey, mXmlValue);
			}
		}
	}
	
	public String get(String key){
		return mHashMap.get(key);
	}
}
