package com.financial.tools.recorderserver.payload;

import java.util.List;

public class FinancialRecordListResponse {

	private List<FinancialRecordResponse> recordList;

	public FinancialRecordListResponse() {
	}

	public FinancialRecordListResponse(List<FinancialRecordResponse> recordList) {
		super();
		this.recordList = recordList;
	}

	public List<FinancialRecordResponse> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<FinancialRecordResponse> recordList) {
		this.recordList = recordList;
	}

}
