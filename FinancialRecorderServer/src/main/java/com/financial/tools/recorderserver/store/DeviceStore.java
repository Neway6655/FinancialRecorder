package com.financial.tools.recorderserver.store;

public interface DeviceStore {

	public void registerDevice(String userName, String deviceRegId);

	public String getDeviceRegId(String userName);

	public void unRegisterDevice(String userName, String deviceRegId);
}
