package com.webin.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebinMain
 */

public class WebinMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageFactory mMessageFactory;//生成消息回复？
	private WebinAuthorize mWebinAuthorize;//自动授权？

    /**
     * Default constructor. 
     */
    public WebinMainServlet() {
    	super();
    	mWebinAuthorize = WebinAuthorize.getInstance();
    	mMessageFactory = MessageFactory.getInstance();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (mMessageFactory != null) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			mMessageFactory.handleMessage(request.getInputStream(), response.getWriter());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if (mWebinAuthorize != null){
//			req.setCharacterEncoding("UTF-8");
//			resp.setCharacterEncoding("UTF-8");
//			mWebinAuthorize.doAuthorize(req, resp.getWriter());
//		}
		doPost(req, resp);
	}
	
	
}
