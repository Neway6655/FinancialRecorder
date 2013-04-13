package com.financial.tools.recorderserver.payload;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.financial.tools.recorderserver.util.CustomJsonDateDeserializer;
import com.financial.tools.recorderserver.util.CustomJsonDateSerializer;

public class FinancialRecordRequest {

	private String name;
	private float totalFee;
	private List<String> userNameList;
	private Date recordDate;

	public FinancialRecordRequest() {
	}

	public FinancialRecordRequest(String name, float totalFee, List<String> userNameList, Date recordDate) {
		this.name = name;
		this.totalFee = totalFee;
		this.userNameList = userNameList;
		this.recordDate = new Date(recordDate.getTime());
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

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getRecordDate() {
		return recordDate;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setRecordDate(Date recordDate) {
		this.recordDate = new Date(recordDate.getTime());
	}

}
