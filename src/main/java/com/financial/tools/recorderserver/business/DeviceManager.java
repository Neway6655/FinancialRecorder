package com.financial.tools.recorderserver.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.store.DeviceStore;

public class DeviceManager {

	private static Logger logger = LoggerFactory.getLogger(DeviceManager.class);

	private DeviceStore deviceStore;

	/**
	 * Register deviceRegId with user.
	 * 
	 * @param userName
	 * @param deviceRegId
	 */
	public void register(String userName, String deviceRegId) {
		logger.debug("Register user: {} with deviceRegId:{}.", userName, deviceRegId);
		deviceStore.registerDevice(userName, deviceRegId);
	}

	public String getRegisteredDeviceId(String userName) {
		return deviceStore.getDeviceRegId(userName);
	}

	public void unRegister(String userName, String deviceRegId) {
		deviceStore.unRegisterDevice(userName, deviceRegId);
	}

	@Autowired
	public void setDeviceStore(DeviceStore deviceStore) {
		this.deviceStore = deviceStore;
	}

}
