
package com.webin.action;



import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.webin.service.MainService;
import com.webin.service.MessageHandler;

public class BaseAction extends ActionSupport {
	protected MessageHandler messageHandler;
	protected MainService mainService;
	@Resource
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	@Resource
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	/**
	* 【获得Request请求对象】
	* 
	* @return（HttpServletRequest对象）
	*/
	public HttpServletRequest getRequest() {
	HttpServletRequest request = ServletActionContext.getRequest();
	try {
	request.setCharacterEncoding("UTF-8");
	} catch (UnsupportedEncodingException e) {
	e.printStackTrace();
	}
	return request;
	}
	/**
	* 【获得Response响应对象】
	* 
	* @return (HttpServletResponse对象)
	*/
	public HttpServletResponse getResponse() {
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/json");
	return response;
	}
	/**
	* 【获得Session会话对象】
	* 
	* @return（HttpSession对象）
	*/
	public HttpSession getSession() {
	return ServletActionContext.getRequest().getSession();
	}
	/**
	* 设置Request的Attribute函数
	* 
	* @param key
	* @param value
	*/
	public void setRequestAttribute(String key, Object value) {
	this.getRequest().setAttribute(key, value);
	}
	/**
	* 取得Request的Attribute存储值
	* 
	* @param key
	* @return
	*/
	public Object getRequestAttribute(String key) {
	return this.getRequest().getAttribute(key);
	}
	/**
	* 取得Request的Parameter存储值
	* 
	* @param key
	* @return
	*/
	public String getRequestParameter(String key) {
	return this.getRequest().getParameter(key);
	}
	/**
	* 设置Session的Attribute函数
	* 
	* @param key
	* @param value
	*/
	public void setSessionAttribute(String key, Object value) {
	this.getSession().setAttribute(key, value);
	}
	/**
	* 取得Session的Attribute存储值
	* 
	* @param key
	* @return
	*/
	public Object getSessionAttribute(String key) {
	return this.getSession().getAttribute(key);
	}
	/**
	* 【取得ServletContext对象】
	* 
	* @return
	*/
	public ServletContext getServletContext() {
	return ServletActionContext.getServletContext();
	}
	}