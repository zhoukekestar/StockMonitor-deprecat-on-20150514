package com.stock.jsp.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public abstract class BaseObject{
	protected HttpServletRequest 	request = null;
	protected Logger 				logger 	= Logger.getLogger(this.getClass());

	public void setRequest(HttpServletRequest request) {
		this.request = request;

		try{
			execute();
		}
		catch(Exception e){
			logger.error("执行异常?");
		}
	}
	
	abstract void execute();
	
}
