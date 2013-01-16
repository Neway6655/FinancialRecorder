package com.financial.tools.recorderserver.payload;

import java.util.List;

import com.financial.tools.recorderserver.entity.FinancialRecord;

public class UserRecordsResponse {

	private List<FinancialRecord> recordList;

	public UserRecordsResponse() {
	}

	public List<FinancialRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<FinancialRecord> recordList) {
		this.recordList = recordList;
	}

}
