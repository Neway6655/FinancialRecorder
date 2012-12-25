package com.financial.tools.recorderserver.payload;

import java.util.Date;
import java.util.List;

public class FinancialRecordResponse {
	private long id;
	private String name;
	private float totalFee;
	private List<String> userNameList;
	private Date recordDate;

	public FinancialRecordResponse() {
	}

	public FinancialRecordResponse(long id, String name, float totalFee, List<String> userNameList, Date recordDate) {
		super();
		this.id = id;
		this.name = name;
		this.totalFee = totalFee;
		this.userNameList = userNameList;
		this.recordDate = new Date(recordDate.getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(float totalFee) {
		this.totalFee = totalFee;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = new Date(recordDate.getTime());
	}

}
