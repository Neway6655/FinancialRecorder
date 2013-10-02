package com.financial.tools.recorderserver.store;

import java.util.List;

public interface UserRecordStore {

	public void addRecord2User(String userName, long financialRecordId);

	public List<Long> findFinancialRecordIdList(String userName);

}
