package com.webin.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.webin.core.WebinLog;
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
import com.webin.util.Parameters;
@Scope("prototype")
@Service
public class MessageHandler{
	protected MainService mainService;
	@Resource
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	public void handleMessage(InputStream inputStream, final PrintWriter writer) throws IOException {

			MessagePullObj mPullObj = new MessagePullObj();
			//������Ϣ
			mPullObj.handleInputStream(inputStream);
			inputStream.close();
			MessagePull msgPull = classifyMessage(mPullObj);
			if (msgPull != null&&"text".equals(msgPull.getMsgType())) {
				//��Ϊ����ָ����û�з���help��classifyMenu(String menu)
				WebinLog.D(msgPull.toString());
				HandleAutoReplay(classifyMenu(msgPull,mPullObj.get("Content")),  writer);
			}else if(msgPull != null&&"location".equals(msgPull.getMsgType())){
				WebinLog.D(msgPull.toString());
				HandleAutoReplay(classifyLocation(msgPull, mPullObj),  writer);
			}else if(msgPull != null&&"event".equals(msgPull.getMsgType())){
				WebinLog.D(msgPull.toString());
				HandleAutoReplay(classifyEvent(msgPull, mPullObj),  writer);
			}
		
	}
	
	private MessagePull classifyMessage(MessagePullObj mPullObj){
		MessagePull vMsgPull = null;
		String vMsgType = mPullObj.get("MsgType");
		//���ݲ�ͬ����Ϣ������������Ӧ�Ĵ���
		if (MessagePull.MSG_TEXT.equals(vMsgType)) {
			//��ͨ�ı���Ϣ
			vMsgPull = new TextMessagePull(mPullObj);
		} else if (MessagePull.MSG_LOCATION.equals(vMsgType)) {
			//λ����Ϣ
			vMsgPull = new LocationMessagePull(mPullObj);
		} else if (MessagePull.MSG_LINK.equals(vMsgType)) {
			//������ͼ����Ϣ
			vMsgPull = new LinkMessagePull(mPullObj);
		} else if (MessagePull.MSG_IMAGE.equals(vMsgType)) {
			//ͼ����Ϣ
			vMsgPull = new ImageMessagePull(mPullObj);
		} else if (MessagePull.MSG_EVENT.equals(vMsgType)) {
			//�����¼���������
			vMsgPull = new EventMessagePull(mPullObj);
		}
		return vMsgPull;
	}
	
	private void HandleAutoReplay(MessagePush messagePush, PrintWriter writer) throws IOException {
		writer.write(messagePush.toString());
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
	
	private MessagePush classifyMenu(MessagePull vMsgPull,String menu){
		//ֱ�Ӵ����￪ʼ���룬
		if("+".equals(menu)){
			//return mainService.get();
			return null;
		}
		return null;
		
	}
	
	private MessagePush classifyLocation(MessagePull vMsgPull,MessagePullObj mPullObj){
		return null;
	}
	
	private MessagePush classifyEvent(MessagePull vMsgPull,MessagePullObj mPullObj){
		TextMessagePush vMsgPush = new TextMessagePush(vMsgPull);
		if("subscribe".equals(mPullObj.get("Event"))){
			vMsgPush.setContent(Parameters.SUBSCRIBE+Parameters.MENU);
			return vMsgPush;
			
		}
		vMsgPush.setContent("���ǻ�ӭ�ʣ������㿴����");
		return vMsgPush;
	}
	

}
