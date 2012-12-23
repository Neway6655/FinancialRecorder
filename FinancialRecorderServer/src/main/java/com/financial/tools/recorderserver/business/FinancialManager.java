package com.financial.tools.recorderserver.business;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.entity.FinancialRecordStatus;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.financial.tools.recorderserver.store.FinancialRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.util.JsonMappingUtils;
import com.google.common.collect.Lists;

public class FinancialManager {

	private static Logger logger = LoggerFactory.getLogger(FinancialManager.class);
	private UserStore userStore;
	private FinancialRecordStore financialRecordStore;

	public long updateUserBalance(String userName, long amount) {
		logger.debug("Update user balance, userName: {}, amount: {}.", userName, amount);
		User user = userStore.getUserByName(userName);
		long balance = user.getBalance() + amount;
		userStore.updateBalance(user.getId(), balance);
		return balance;
	}

	public long createFinancialRecord(FinancialRecordRequest financialRecordRequest) {
		logger.debug("Create financial record, request: {}.",
				JsonMappingUtils.getJsonStringWithoutExceptionOut(financialRecordRequest));
		FinancialRecord financialRecord = new FinancialRecord();

		financialRecord.setName(financialRecordRequest.getName());
		financialRecord.setTotalFee(financialRecordRequest.getTotalFee());
		financialRecord.setStatus(FinancialRecordStatus.NEW.getValue());

		String userNames = StringUtils.join(financialRecordRequest.getUserNameList(), ",");
		financialRecord.setUserNames(userNames);

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
			for (String userName : financialRecord.getUserNames().split(",")) {
				userNameList.add(userName);
			}

			record.setUserNameList(userNameList);
			recordList.add(record);
		}
		return recordList;
	}

	public void updateFinance(long financialRecordId) {
		logger.debug("Update financial record with id: {}.", financialRecordId);
		FinancialRecord financialRecord = financialRecordStore.getFinancialRecord(financialRecordId);
		if (financialRecord != null) {
			financialRecord.setStatus(FinancialRecordStatus.UPDATED.getValue());
			financialRecordStore.updateFinancialRecord(financialRecord);

			long totalFee = financialRecord.getTotalFee();
			String[] userNameArray = financialRecord.getUserNames().split(",");
			long feePerUser = totalFee / userNameArray.length;
			for (String userName : userNameArray) {
				User user = userStore.getUserByName(userName);
				logger.debug("deduct user fee, userId: {}, fee: {}.", user.getId(), feePerUser);
				userStore.updateBalance(user.getId(), user.getBalance() - feePerUser);
			}
		}
	}

	public UserFinancialInfoResponse getUserFinancialInfo(long userId) {
		logger.debug("Get user financial info with user id: {}.", userId);
		User user = userStore.getUser(userId);
		UserFinancialInfoResponse userInfo = new UserFinancialInfoResponse();
		if (user != null) {
			userInfo = new UserFinancialInfoResponse(user.getName(), user.getBalance());
		}
		return userInfo;
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
