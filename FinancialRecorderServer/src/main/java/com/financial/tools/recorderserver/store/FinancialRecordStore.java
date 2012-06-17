package com.financial.tools.recorderserver.store;

import com.financial.tools.recorderserver.entity.FinancialRecord;

public interface FinancialRecordStore {

	public void createFinancialRecord(FinancialRecord financialRecord);

	public FinancialRecord getFinancialRecord(long financialRecordId);
}
