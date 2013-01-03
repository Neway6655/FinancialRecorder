package com.financial.tools.recorderserver.store;

import java.util.List;

import com.financial.tools.recorderserver.entity.FinancialRecord;

public interface UserRecordStore {

	public void addRecord2User(String userName, FinancialRecord record);

	public List<FinancialRecord> findFinancialRecordList(String userName);
}
