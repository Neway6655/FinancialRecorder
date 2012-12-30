package com.financial.tools.recorderserver.exception;

public class FinancialRecorderException extends RuntimeException {

	private static final long serialVersionUID = 6609946372166258821L;

	private ErrorCode code;

	private String description;

	private final int STATUS_CODE = 400;

	public FinancialRecorderException(ErrorCode code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatusCode() {
		return STATUS_CODE;
	}

	public String getErrorBody() {
		return code + " : " + description;
	}

}
