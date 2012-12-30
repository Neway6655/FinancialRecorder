package com.financial.tools.recorderserver.exception;

public enum ErrorCode {

	LOGIN_ERROR("login_error");

	private String errorCode;

	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
