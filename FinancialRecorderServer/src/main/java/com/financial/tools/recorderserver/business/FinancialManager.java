package com.financial.tools.recorderserver.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.financial.tools.recorderserver.store.FinancialRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.google.common.collect.Lists;

public class FinancialManager {

	private UserStore userStore;
	private FinancialRecordStore financialRecordStore;

	public long updateUserBalance(long userId, long amount) {
		User user = userStore.getUser(userId);
		long balance = user.getBalance() + amount;
		userStore.updateBalance(user.getId(), balance);
		return balance;
	}

	public long createFinancialRecord(FinancialRecordRequest financialRecordRequest) {
		List<User> userList = Lists.newArrayList();
		for (Long userId : financialRecordRequest.getUserIdList()) {
			userList.add(userStore.getUser(userId));
		}

		FinancialRecord financialRecord = new FinancialRecord();
		financialRecord.setName(financialRecordRequest.getName());
		financialRecord.setTotalFee(financialRecordRequest.getTotalFee());
		financialRecord.setUserList(userList);

		return financialRecordStore.createFinancialRecord(financialRecord);
	}

	public List<FinancialRecordResponse> listFinancialRecords() {
		List<FinancialRecordResponse> recordList = Lists.newArrayList();
		List<FinancialRecord> financialRecordList = financialRecordStore.listFinancialRecords();
		for (FinancialRecord financialRecord : financialRecordList) {
			FinancialRecordResponse record = new FinancialRecordResponse();
			record.setName(financialRecord.getName());
			record.setTotalFee(financialRecord.getTotalFee());

			List<String> userNameList = Lists.newArrayList();
			for (User user : financialRecord.getUserList()) {
				userNameList.add(user.getName());
			}

			record.setUserNameList(userNameList);
			recordList.add(record);
		}
		return recordList;
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
