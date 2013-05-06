package com.financial.tools.recorderserver.business;

import java.util.Date;
import java.util.List;

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
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.financial.tools.recorderserver.store.BudgetTrailStore;
import com.financial.tools.recorderserver.store.DeviceStore;
import com.financial.tools.recorderserver.store.FinancialRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.util.CopyUtils;
import com.financial.tools.recorderserver.util.JsonMappingUtils;
import com.financial.tools.recorderserver.util.NotificationHelper;
import com.financial.tools.recorderserver.util.NotificationType;
import com.google.common.collect.Lists;

public class FinancialManager {

	private static Logger logger = LoggerFactory.getLogger(FinancialManager.class);
	private UserStore userStore;
	private FinancialRecordStore financialRecordStore;
	private BudgetTrailStore budgetTrailStore;
	private DeviceStore deviceStore;

	public float cashin(String userName, float amount) {
		logger.debug("Update user balance, userName: {}, amount: {}.", userName, amount);
		User user = userStore.getUserByName(userName);
		float balance = user.getBalance() + amount;
		userStore.updateBalance(user.getId(), balance);

		budgetTrailStore.createBudgetTrail(new BudgetTrail(userName, BudgetTrailType.CASH_IN.getValue(), new Date(),
				amount));

		return balance;
	}

	public long createFinancialRecord(FinancialRecord financialRecord) {
		logger.debug("Create financial record: {}.", JsonMappingUtils.getJsonStringWithoutExceptionOut(financialRecord));
		long financialRecordId = financialRecordStore.createFinancialRecord(financialRecord);

		return financialRecordId;
	}

	public List<FinancialRecordResponse> listFinancialRecordsByStatus(FinancialRecordStatus status) {
		List<FinancialRecordResponse> recordList = Lists.newArrayList();
		List<FinancialRecord> financialRecordList = financialRecordStore
				.listFinancialRecordsByStatus(status.getValue());
		for (FinancialRecord financialRecord : financialRecordList) {
			FinancialRecordResponse recordResponse = CopyUtils.convertFinancialRecord2Response(financialRecord);
			recordList.add(recordResponse);
		}
		return recordList;
	}

	public FinancialRecord getFinancialRecord(long financialRecordId) {
		if (financialRecordId != 0) {
			return financialRecordStore.getFinancialRecord(financialRecordId);
		}
		return null;
	}

	public FinancialRecord updateFinancialRecord(FinancialRecord financialRecord) {
		if (financialRecord != null) {
			logger.debug("Update financial record with ID: {}.", financialRecord.getId());
			return financialRecordStore.updateFinancialRecord(financialRecord);
		}
		return null;
	}

	public void deductFee(long financialRecordId, float totalFee) {
		logger.debug("Update financial record with id: {}.", financialRecordId);
		FinancialRecord financialRecord = financialRecordStore.getFinancialRecord(financialRecordId);
		if (financialRecord != null) {
			financialRecord.setStatus(FinancialRecordStatus.UPDATED.getValue());
			financialRecord.setTotalFee(totalFee);
			financialRecordStore.updateFinancialRecord(financialRecord);

			String[] userNameArray = financialRecord.getUserNames().split(",");
			float feePerUser = totalFee / userNameArray.length;
			Date budgetTrailCreatedTime = new Date();
			for (String userName : userNameArray) {
				User user = userStore.getUserByName(userName);
				if (user == null) {
					throw new FinancialRecorderException(ErrorCode.INTERNAL_ERROR, "Server internal error.");
				}
				logger.debug("deduct user fee, userName: {}, fee: {}.", userName, feePerUser);
				userStore.updateBalance(user.getId(), user.getBalance() - feePerUser);

				budgetTrailStore.createBudgetTrail(new BudgetTrail(userName, BudgetTrailType.PAY_FEE.getValue(),
						budgetTrailCreatedTime, feePerUser));

				// send notification.
				String deviceRegId = deviceStore.getDeviceRegId(userName);
				String notificationMessage = String.format("Hi %1$s, deduct fee %2$.2f RMB.", userName, feePerUser);
				NotificationHelper.sendNotification(deviceRegId, NotificationType.DEDUCT_FEE, notificationMessage, -1);
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

	@Autowired
	public void setDeviceStore(DeviceStore deviceStore) {
		this.deviceStore = deviceStore;
	}

}
