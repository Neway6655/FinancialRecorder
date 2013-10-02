package com.financial.tools.recorderserver.util.timer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.business.DeviceManager;
import com.financial.tools.recorderserver.business.UserManager;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.util.NotificationHelper;
import com.financial.tools.recorderserver.util.NotificationType;

public class AccountCheckingTimer {

	private static final double MIN_BALANCE = 20.0;

	private static Logger logger = LoggerFactory.getLogger(AccountCheckingTimer.class);

	private UserManager userManager;

	private DeviceManager deviceManager;

	public void execute() {
		logger.info("account checking timer executed.");

		List<User> users = userManager.listUsers();
		for (User user : users) {
			if (isBalanceNotEnough(user)) {
				notifyUserToCashin(user);
			}
		}
	}

	private boolean isBalanceNotEnough(User user) {
		return user.getBalance() <= MIN_BALANCE;
	}

	private void notifyUserToCashin(User user) {
		logger.debug("Send notification to user {} to cash in.", user.getName());
		String deviceRegId = deviceManager.getRegisteredDeviceId(user.getName());
		String notificationMessage = String.format("Hi %1$s, your balance is limit: %2$.2f RMB. Please cash in.",
				user.getName(), user.getBalance());
		NotificationHelper.sendNotification(deviceRegId, NotificationType.REMINDER, notificationMessage, -1);
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

}
