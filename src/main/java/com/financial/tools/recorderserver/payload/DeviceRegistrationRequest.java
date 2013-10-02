package com.financial.tools.recorderserver.payload;

public class DeviceRegistrationRequest {

	private String userName;

	private String deviceRegId;

	public DeviceRegistrationRequest() {
	}

	public DeviceRegistrationRequest(String userName, String deviceRegId) {
		this.userName = userName;
		this.deviceRegId = deviceRegId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceRegId() {
		return deviceRegId;
	}

	public void setDeviceRegId(String deviceRegId) {
		this.deviceRegId = deviceRegId;
	}

}
