package com.financial.tools.recorderserver.store;

import java.util.List;

import com.financial.tools.recorderserver.entity.FinancialRecord;

public interface FinancialRecordStore {

	public long createFinancialRecord(FinancialRecord financialRecord);

	public FinancialRecord getFinancialRecord(long financialRecordId);

	public List<FinancialRecord> listFinancialRecords();

	FinancialRecord updateFinancialRecord(FinancialRecord financialRecord);
}
