package com.financial.tools.recorderserver.exception;

public enum ErrorCode {

	LOGIN_ERROR("login_error"),

	USER_ALREADY_EXIST_ERROR("user_already_exist_error"),

	USER_NOT_EXIST_ERROR("user_not_exist_error"),

	PASSWORD_INVALID_ERROR("password_invalid_error"),

	INTERNAL_ERROR("internal_erro"),

	MISSING_PARAMETER_ERROR("missing_parameter_error");

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
