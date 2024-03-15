package com.sprint.cbm.exception;

import java.util.Date;

public class CustomErrorResponse {
	private int status;
	private String msg;
	private Date timestamp;

	public CustomErrorResponse(int status, String msg, Date timestamp) {
	        super();
	        this.status = status;
	        this.msg = msg;
	        this.timestamp = timestamp;
	    }

	public CustomErrorResponse(){
	        super();
	    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date date) {
		this.timestamp = date;
	}

}
