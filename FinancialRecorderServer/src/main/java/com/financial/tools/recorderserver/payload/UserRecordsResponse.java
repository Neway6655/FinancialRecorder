package com.financial.tools.recorderserver.payload;

import java.util.List;

public class UserRecordsResponse {

	private List<FinancialRecordResponse> recordList;

	public UserRecordsResponse() {
	}

	public List<FinancialRecordResponse> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<FinancialRecordResponse> recordList) {
		this.recordList = recordList;
	}

}
