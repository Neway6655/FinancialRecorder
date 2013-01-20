package com.financial.tools.recorderserver.business;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.BudgetTrail;
import com.financial.tools.recorderserver.entity.BudgetTrailType;
import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.entity.FinancialRecordStatus;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.exception.ErrorCode;
import com.financial.tools.recorderserver.exception.FinancialRecorderException;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.financial.tools.recorderserver.store.BudgetTrailStore;
import com.financial.tools.recorderserver.store.FinancialRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.util.JsonMappingUtils;
import com.google.common.collect.Lists;

public class FinancialManager {

	private static Logger logger = LoggerFactory.getLogger(FinancialManager.class);
	private UserStore userStore;
	private FinancialRecordStore financialRecordStore;
	private BudgetTrailStore budgetTrailStore;

	public float cashin(String userName, float amount) {
		logger.debug("Update user balance, userName: {}, amount: {}.", userName, amount);
		User user = userStore.getUserByName(userName);
		float balance = user.getBalance() + amount;
		userStore.updateBalance(user.getId(), balance);

		budgetTrailStore.createBudgetTrail(new BudgetTrail(userName, BudgetTrailType.CASH_IN.getValue(), new Date(),
				amount));

		return balance;
	}

	public long createFinancialRecord(FinancialRecordRequest financialRecordRequest) {
		logger.debug("Create financial record, request: {}.",
				JsonMappingUtils.getJsonStringWithoutExceptionOut(financialRecordRequest));
		FinancialRecord financialRecord = new FinancialRecord();

		financialRecord.setName(financialRecordRequest.getName());
		financialRecord.setTotalFee(financialRecordRequest.getTotalFee());
		financialRecord.setStatus(FinancialRecordStatus.NEW.getValue());
		financialRecord.setRecordDate(financialRecordRequest.getRecordDate() == null ? new Date()
				: financialRecordRequest.getRecordDate());

		String userNames = StringUtils.join(financialRecordRequest.getUserNameList(), ",");
		financialRecord.setUserNames(userNames);

		return financialRecordStore.createFinancialRecord(financialRecord);
	}

	public List<FinancialRecordResponse> listFinancialRecords() {
		List<FinancialRecordResponse> recordList = Lists.newArrayList();
		List<FinancialRecord> financialRecordList = financialRecordStore.listFinancialRecords();
		for (FinancialRecord financialRecord : financialRecordList) {
			FinancialRecordResponse record = new FinancialRecordResponse();
			record.setId(financialRecord.getId());
			record.setName(financialRecord.getName());
			record.setTotalFee(financialRecord.getTotalFee());
			record.setRecordDate(financialRecord.getRecordDate());

			List<String> userNameList = Lists.newArrayList();
			for (String userName : financialRecord.getUserNames().split(",")) {
				userNameList.add(userName);
			}

			record.setUserNameList(userNameList);
			recordList.add(record);
		}
		return recordList;
	}

	public void deductFee(long financialRecordId) {
		logger.debug("Update financial record with id: {}.", financialRecordId);
		FinancialRecord financialRecord = financialRecordStore.getFinancialRecord(financialRecordId);
		if (financialRecord != null) {
			financialRecord.setStatus(FinancialRecordStatus.UPDATED.getValue());
			financialRecordStore.updateFinancialRecord(financialRecord);

			float totalFee = financialRecord.getTotalFee();
			String[] userNameArray = financialRecord.getUserNames().split(",");
			float feePerUser = totalFee / userNameArray.length;
			Date budgetTrailCreatedTime = new Date();
			for (String userName : userNameArray) {
				User user = userStore.getUserByName(userName);
				if (user == null) {
					throw new FinancialRecorderException(ErrorCode.INTERNAL_ERROR, "Server internal error.");
				}
				logger.debug("deduct user fee, userId: {}, fee: {}.", user.getId(), feePerUser);
				userStore.updateBalance(user.getId(), user.getBalance() - feePerUser);

				budgetTrailStore.createBudgetTrail(new BudgetTrail(userName, BudgetTrailType.PAY_FEE.getValue(),
						budgetTrailCreatedTime, feePerUser));
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

	public List<BudgetTrail> searchUserBudgetTrail(String userName) {
		return budgetTrailStore.findBudgetTrailList(userName);
	}

	@Autowired
	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

	@Autowired
	public void setFinancialRecordStore(FinancialRecordStore financialRecordStore) {
		this.financialRecordStore = financialRecordStore;
	}

	@Autowired
	public void setBudgetTrailStore(BudgetTrailStore budgetTrailStore) {
		this.budgetTrailStore = budgetTrailStore;
	}

}
