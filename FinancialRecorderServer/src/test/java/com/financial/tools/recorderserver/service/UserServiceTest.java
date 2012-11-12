package com.financial.tools.recorderserver.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.financial.tools.recorderserver.AbstractComponentTestCase;
import com.financial.tools.recorderserver.client.UserServiceClient;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.CreateUserRequest;

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
		List<User> userList = userServiceClient.listUsers();
		Assert.assertEquals(3, userList.size());
	}

}
