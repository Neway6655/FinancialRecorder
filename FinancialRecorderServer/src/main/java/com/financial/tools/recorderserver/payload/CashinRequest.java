package com.financial.tools.recorderserver.payload;

public class CashinRequest {

	private long userId;

	private long amount;

	public CashinRequest() {
	}

	public CashinRequest(long userId, long amount) {
		this.userId = userId;
		this.amount = amount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
