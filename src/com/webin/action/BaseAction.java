
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
	* �����Request�������
	* 
	* @return��HttpServletRequest����
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
	* �����Response��Ӧ����
	* 
	* @return (HttpServletResponse����)
	*/
	public HttpServletResponse getResponse() {
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/json");
	return response;
	}
	/**
	* �����Session�Ự����
	* 
	* @return��HttpSession����
	*/
	public HttpSession getSession() {
	return ServletActionContext.getRequest().getSession();
	}
	/**
	* ����Request��Attribute����
	* 
	* @param key
	* @param value
	*/
	public void setRequestAttribute(String key, Object value) {
	this.getRequest().setAttribute(key, value);
	}
	/**
	* ȡ��Request��Attribute�洢ֵ
	* 
	* @param key
	* @return
	*/
	public Object getRequestAttribute(String key) {
	return this.getRequest().getAttribute(key);
	}
	/**
	* ȡ��Request��Parameter�洢ֵ
	* 
	* @param key
	* @return
	*/
	public String getRequestParameter(String key) {
	return this.getRequest().getParameter(key);
	}
	/**
	* ����Session��Attribute����
	* 
	* @param key
	* @param value
	*/
	public void setSessionAttribute(String key, Object value) {
	this.getSession().setAttribute(key, value);
	}
	/**
	* ȡ��Session��Attribute�洢ֵ
	* 
	* @param key
	* @return
	*/
	public Object getSessionAttribute(String key) {
	return this.getSession().getAttribute(key);
	}
	/**
	* ��ȡ��ServletContext����
	* 
	* @return
	*/
	public ServletContext getServletContext() {
	return ServletActionContext.getServletContext();
	}
	}