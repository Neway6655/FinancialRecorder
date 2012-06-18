package com.financial.tools.recorderserver.payload;

import java.util.List;

public class FinancialRecordRequest {

	private String name;
	private long totalFee;
	private List<Long> userIdList;

	public FinancialRecordRequest() {
	}

	public FinancialRecordRequest(String name, long totalFee, List<Long> userIdList) {
		this.name = name;
		this.totalFee = totalFee;
		this.userIdList = userIdList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public List<Long> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}

}
