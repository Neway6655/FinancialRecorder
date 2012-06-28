package com.financial.tools.recorderserver.payload;

import java.util.List;

public class FinancialRecordListResponse {
	private String name;
	private long totalFee;
	private List<String> userNameList;

	public FinancialRecordListResponse() {
	}

	public FinancialRecordListResponse(String name, long totalFee, List<String> userNameList) {
		super();
		this.name = name;
		this.totalFee = totalFee;
		this.userNameList = userNameList;
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

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

}
