package com.webin.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.webin.core.pull.EventMessagePull;
import com.webin.core.pull.ImageMessagePull;
import com.webin.core.pull.LinkMessagePull;
import com.webin.core.pull.LocationMessagePull;
import com.webin.core.pull.MessagePull;
import com.webin.core.pull.MessagePullObj;
import com.webin.core.pull.TextMessagePull;
import com.webin.core.push.ImgTextMessagePush;
import com.webin.core.push.MessagePush;
import com.webin.core.push.MusicMessagePush;
import com.webin.core.push.TextMessagePush;

public class MessageFactory extends Thread{
	private static MessageFactory _inst = null;
	private MessagePullObj mPullObj;
	
	private MessageFactory(){
		mPullObj = new MessagePullObj();
	}

	public static MessageFactory getInstance() {
		if (_inst==null){
			_inst = new MessageFactory();
		}
		return _inst;
	}
	
	public synchronized void handleMessage(InputStream inputStream, final PrintWriter writer) throws IOException {

		if (mPullObj != null) {
			//解析信息
//			System.out.println(mPullObj.toString()+"传递过来的对象"+"  "+mPullObj.get("ToUserName")+" "+mPullObj.get("Content"));
//			System.out.println("inputStream的id----"+inputStream);
			mPullObj.handleInputStream(inputStream);
			//信息分类
			MessagePull msgPull = classifyMessage(mPullObj);
//			System.out.println(msgPull.toString());
			if (msgPull != null) {
				WebinLog.D(msgPull.toString());
				HandleAutoReplay(msgPull, writer);
			}
		}
		inputStream.close();
	}
	
	private synchronized MessagePull classifyMessage(MessagePullObj msgobj){
		MessagePull vMsgPull = null;
		String vMsgType = mPullObj.get("MsgType");
		//根据不同的信息类型来进行相应的处理
		if (MessagePull.MSG_TEXT.equals(vMsgType)) {
			//普通文本信息
			vMsgPull = new TextMessagePull(mPullObj);
		} else if (MessagePull.MSG_LOCATION.equals(vMsgType)) {
			//位置信息
			vMsgPull = new LocationMessagePull(mPullObj);
		} else if (MessagePull.MSG_LINK.equals(vMsgType)) {
			//带链接图文信息
			vMsgPull = new LinkMessagePull(mPullObj);
		} else if (MessagePull.MSG_IMAGE.equals(vMsgType)) {
			//图文信息
			vMsgPull = new ImageMessagePull(mPullObj);
		} else if (MessagePull.MSG_EVENT.equals(vMsgType)) {
			//基于事件处理类型
			vMsgPull = new EventMessagePull(mPullObj);
		}
		return vMsgPull;
	}
	
	private void HandleAutoReplay(MessagePull vMsgPull, PrintWriter writer) throws IOException {
		TextMessagePush msg = (TextMessagePush) makeMessagePush(vMsgPull, MessagePush.MSG_TEXT);
		msg.setContent(vMsgPull.getMsgId()+"msgid");
		msg.setFuncFlag("0");
//		writer.print(msg);
		writer.write(msg.toString());
//		System.out.println(msg.toString());
//		WebinLog.D(msg.toString());
		writer.flush();
		writer.close();
	}
	
	private MessagePush makeMessagePush(MessagePull vMsgPull, String vMsgType){
		MessagePush vMsgPush = null;
		if (MessagePush.MSG_TEXT.equals(vMsgType)){
			vMsgPush = new TextMessagePush(vMsgPull);
		}else if (MessagePush.MSG_NEWS.equals(vMsgType)){
			vMsgPush = new ImgTextMessagePush(vMsgPull);
		}else if (MessagePush.MSG_MUSIC.equals(vMsgType)){
			vMsgPush = new MusicMessagePush(vMsgPull);
		}
		return vMsgPush;
	}
}
