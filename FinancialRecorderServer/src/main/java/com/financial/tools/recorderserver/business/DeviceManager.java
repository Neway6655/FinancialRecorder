package com.financial.tools.recorderserver.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.store.DeviceStore;

public class DeviceManager {

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

	public String getRegisteredDeviceId(String userName) {
		return deviceStore.getDeviceRegId(userName);
	}

	@Autowired
	public void setDeviceStore(DeviceStore deviceStore) {
		this.deviceStore = deviceStore;
	}

}
