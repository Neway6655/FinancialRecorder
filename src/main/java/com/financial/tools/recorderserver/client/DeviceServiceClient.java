package com.financial.tools.recorderserver.client;

import javax.ws.rs.core.MediaType;

import com.financial.tools.recorderserver.payload.DeviceRegistrationRequest;

public class DeviceServiceClient extends AbstractFinancialClient {

	public DeviceServiceClient(String serverAddress) {
		super(serverAddress);
	}

	public String register(DeviceRegistrationRequest request) {
		client.path("device/register").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, request, String.class);
	}

	public String getDeviceRegisterId(String userName) {
		client.path("device/getRegId").query("userName", userName).accept(MediaType.TEXT_PLAIN);
		return get(client, String.class);
	}
}
