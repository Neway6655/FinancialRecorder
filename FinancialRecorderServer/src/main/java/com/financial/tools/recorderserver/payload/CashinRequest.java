package com.financial.tools.recorderserver.payload;


public class CashinRequest {

	private String userId;

	public CashinRequest() {
	}

	public CashinRequest(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
