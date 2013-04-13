package com.financial.tools.recorderserver.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.entity.UserType;
import com.financial.tools.recorderserver.exception.ErrorCode;
import com.financial.tools.recorderserver.exception.FinancialRecorderException;
import com.financial.tools.recorderserver.store.DeviceStore;
import com.financial.tools.recorderserver.store.UserRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.util.NotificationHelper;

//TODO: Neway, to be full fill later.
public class UserManager {

	private UserRecordStore userRecordStore;

	private UserStore userStore;

	private DeviceStore deviceStore;

	public void addUserRecord(String userName, long financialRecordId) {
		userRecordStore.addRecord2User(userName, financialRecordId);

		// send notification to admin.
		List<User> adminUserList = userStore.findUserByType(UserType.ADMIN.getValue());
		if (adminUserList.isEmpty()) {
			throw new FinancialRecorderException(ErrorCode.USER_NOT_EXIST_ERROR, "Admin user not existed.");
		}
		String deviceRegId = deviceStore.getDeviceRegId(adminUserList.get(0).getName());
		String notificationMessage = String.format("User: %1$s has joined.", userName);
		NotificationHelper.sendNotification(deviceRegId, "Join Activity", notificationMessage, 600);
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
