package com.financial.tools.recorderserver.business;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.store.DeviceStore;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class DeviceManager {

	private static final String GCM_MESSAGE_KEY = "message";

	private static final String GCM_KEY = "AIzaSyDm5fOxjtm38z6oSxdzlwttQo0clbrKFC4";

	private static final int RETRY_TIMES = 3;

	private static Logger logger = LoggerFactory.getLogger(DeviceManager.class);

	private DeviceStore deviceStore;

	/**
	 * Register deviceRegId with user.
	 * 
	 * @param userName
	 * @param deviceRegId
	 */
	public void register(String userName, String deviceRegId) {
		deviceStore.registerDevice(userName, deviceRegId);
	}

	/**
	 * Send notification to user.
	 * 
	 * @param userName
	 */
	public void sendNotification(String userName, String notificationMessage) {
		Message message = new Message.Builder().addData(GCM_MESSAGE_KEY, notificationMessage).build();
		Sender sender = new Sender(GCM_KEY);
		String registrationId = deviceStore.getDeviceRegId(userName);
		if (StringUtils.isEmpty(registrationId)) {
			logger.debug("user: " + userName + " has not registered yet.");
			return;
		}

		try {
			Result result = sender.send(message, registrationId, RETRY_TIMES);
			logger.debug("notification send to device: " + result);
		} catch (IOException e) {
			logger.error("Can't send notification to user: " + userName);
		}
	}

	public String getRegisteredDeviceId(String userName) {
		return deviceStore.getDeviceRegId(userName);
	}

	@Autowired
	public void setDeviceStore(DeviceStore deviceStore) {
		this.deviceStore = deviceStore;
	}

}
