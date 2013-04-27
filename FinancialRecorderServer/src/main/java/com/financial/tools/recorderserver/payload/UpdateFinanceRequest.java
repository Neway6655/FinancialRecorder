package com.financial.tools.recorderserver.payload;

public class UpdateFinanceRequest {

	private String financialRecordId;

	private float totalFee;

	public UpdateFinanceRequest() {
	}

	public UpdateFinanceRequest(String financialRecordId, float totalFee) {
		this.financialRecordId = financialRecordId;
		this.totalFee = totalFee;
	}

	public String getFinancialRecordId() {
		return financialRecordId;
	}

	public void setFinancialRecordId(String financialRecordId) {
		this.financialRecordId = financialRecordId;
	}

	public float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(float totalFee) {
		this.totalFee = totalFee;
	}

}
