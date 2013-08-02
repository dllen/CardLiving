package com.webin.action;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class PullAction extends BaseAction{
	/*
	 * 类：MessageHandler
	 * 方法：handle(InputStream inputStream, PrintWriter writer,MessagePullObj mPullObj)
	 * 变量 MessageHandler   MessagePullObj
	 */
	public String execute(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		try {
			messageHandler.handleMessage(request.getInputStream(), response.getWriter());
		} catch (IOException e) {
			System.out.println("--------------!!!!!!!!!!!!!!!catch到异常!!!!!!!!!!!!!!-----------");
			e.printStackTrace();
		}
		
		return null;
	}
}
