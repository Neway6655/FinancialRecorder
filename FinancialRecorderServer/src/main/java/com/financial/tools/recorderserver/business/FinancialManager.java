package com.financial.tools.recorderserver.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.financial.tools.recorderserver.store.FinancialRecordStore;
import com.financial.tools.recorderserver.store.UserStore;

public class FinancialManager {

	private UserStore userStore;
	private FinancialRecordStore financialRecordStore;

	public void updateUserBalance(long userId, long amount) {
		User user = userStore.getUser(userId);
		long currentBalance = user.getBalance();
		userStore.updateBalance(user.getId(), currentBalance + amount);
	}

	public void createFinancialRecord(FinancialRecord financialRecord) {
		financialRecordStore.createFinancialRecord(financialRecord);
	}

	public void updateFinance(long financialRecordId) {
		FinancialRecord financialRecord = financialRecordStore.getFinancialRecord(financialRecordId);
		long totalFee = financialRecord.getTotalFee();
		List<User> userList = financialRecord.getUserList();
		long feePerUser = totalFee / userList.size();
		for (User user : userList) {
			userStore.updateBalance(user.getId(), user.getBalance() - feePerUser);
		}
	}

	public UserFinancialInfoResponse getUserFinancialInfo(long userId) {
		User user = userStore.getUser(userId);
		return new UserFinancialInfoResponse(user.getName(), user.getBalance());
	}

	@Autowired
	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

	@Autowired
	public void setFinancialRecordStore(FinancialRecordStore financialRecordStore) {
		this.financialRecordStore = financialRecordStore;
	}

}
