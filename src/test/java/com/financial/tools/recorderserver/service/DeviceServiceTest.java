package com.financial.tools.recorderserver.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.financial.tools.recorderserver.AbstractComponentTestCase;
import com.financial.tools.recorderserver.client.DeviceServiceClient;
import com.financial.tools.recorderserver.payload.DeviceRegistrationRequest;

public class DeviceServiceTest extends AbstractComponentTestCase {

	private DeviceServiceClient deviceServiceClient;

	@Before
	public void setUp() {
		String serverAddress = baseUrl + "/api/";
		deviceServiceClient = new DeviceServiceClient(serverAddress);
	}

	@Test
	public void testRegisterDevice() {
		// prepare.
		String userName = "Joker";
		String regId = "AndroidRegisterId";
		DeviceRegistrationRequest request = new DeviceRegistrationRequest(userName, regId);

		// replay.
		deviceServiceClient.register(request);

		// verify.
		String deviceRegisterId = deviceServiceClient.getDeviceRegisterId(userName);
		assertEquals(regId, deviceRegisterId);
	}
}
