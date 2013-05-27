package com.financial.tools.recorderserver.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.entity.UserType;
import com.financial.tools.recorderserver.exception.ErrorCode;
import com.financial.tools.recorderserver.exception.FinancialRecorderException;
import com.financial.tools.recorderserver.store.DeviceStore;
import com.financial.tools.recorderserver.store.UserRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.util.NotificationHelper;
import com.financial.tools.recorderserver.util.NotificationType;

//TODO: Neway, to be full fill later.
public class UserManager {

	private static Logger logger = LoggerFactory.getLogger(UserManager.class);

	private UserRecordStore userRecordStore;

	private UserStore userStore;

	private DeviceStore deviceStore;

	public void addUserRecord(String userName, long financialRecordId) {
		logger.debug("Add user:{} to financial record:{}.", new Object[] { userName, financialRecordId });
		userRecordStore.addRecord2User(userName, financialRecordId);

		// send notification to admin.
		List<User> adminUserList = userStore.findUserByType(UserType.ADMIN.getValue());
		if (adminUserList.isEmpty()) {
			throw new FinancialRecorderException(ErrorCode.USER_NOT_EXIST_ERROR, "Admin user not existed.");
		}

		for (User admin : adminUserList) {
			String adminUserName = admin.getName();
			String deviceRegId = deviceStore.getDeviceRegId(adminUserName);
			logger.debug("Get deviceRegId: {} of admin user: {}.", deviceRegId, adminUserName);

			String notificationMessage = String.format("User: %1$s has joined.", userName);
			NotificationHelper.sendNotification(deviceRegId, NotificationType.JOIN_ACTIVITY, notificationMessage, 600);
		}
	}

	/**
	 * List all users.
	 * 
	 * @return user list.
	 */
	public List<User> listUsers() {
		return userStore.findAll();
	}

	@Autowired
	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

	@Autowired
	public void setUserRecordStore(UserRecordStore userRecordStore) {
		this.userRecordStore = userRecordStore;
	}

	@Autowired
	public void setDeviceStore(DeviceStore deviceStore) {
		this.deviceStore = deviceStore;
	}

}
