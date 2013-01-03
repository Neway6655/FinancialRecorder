package com.financial.tools.recorderserver.payload;

import java.util.List;

import com.financial.tools.recorderserver.entity.FinancialRecord;

public class UserRecordsResponse {

	private List<FinancialRecord> records;

	public UserRecordsResponse() {
	}

	public List<FinancialRecord> getRecords() {
		return records;
	}

	public void setRecords(List<FinancialRecord> records) {
		this.records = records;
	}

}
