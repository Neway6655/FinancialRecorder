package com.financial.tools.recorderserver.service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.financial.tools.recorderserver.AbstractComponentTestCase;
import com.financial.tools.recorderserver.client.UserServiceClient;
import com.financial.tools.recorderserver.payload.CreateUserRequest;
import com.financial.tools.recorderserver.payload.UserListResponse;

public class UserServiceTest extends AbstractComponentTestCase {

	private UserServiceClient userServiceClient;

	@Before
	public void setUp() {
		String serverAddress = baseUrl + "/api/";
		userServiceClient = new UserServiceClient(serverAddress);
	}

	@Test
	public void testCreateAndListUser() {
		// prepare.
		CreateUserRequest request = new CreateUserRequest("userA", "1234", 100L);

		// replay.
		userServiceClient.createUser(request);

		// verify.
		UserListResponse response = userServiceClient.listUsers();
		Assert.assertEquals(3, response.getUserList().size());
	}

}
