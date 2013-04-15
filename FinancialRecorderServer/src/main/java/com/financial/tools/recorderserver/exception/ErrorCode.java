package com.financial.tools.recorderserver.exception;

public enum ErrorCode {

	LOGIN_ERROR("login_error"),

	USER_ALREADY_EXIST_ERROR("user_already_exist_error"),

	USER_NOT_EXIST_ERROR("user_not_exist_error"),

	PASSWORD_INVALID_ERROR("password_invalid_error"),

	INTERNAL_ERROR("internal_erro"),

	MISSING_PARAMETER_ERROR("missing_parameter_error"),

	FINANCIAL_RECORD_NOT_EXIST_ERROR("financial_record_not_exist_error"),

	FINANCIALRECORD_STATUS_INVALID_ERROR("financial_record_status_invalid");

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
