package com.android.kechong.lib.http.Exception;

public class RequestInterruptedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	public RequestInterruptedException( ) { 
	}
	public RequestInterruptedException(String msg) { 
		super();
		this.msg= msg;
	}
	@Override
	public String getMessage() {
		return msg;
	}
}
