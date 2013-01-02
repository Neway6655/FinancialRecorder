package com.financial.tools.recorderserver.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.financial.tools.recorderserver.AbstractComponentTestCase;
import com.financial.tools.recorderserver.client.UserServiceClient;
import com.financial.tools.recorderserver.entity.UserType;
import com.financial.tools.recorderserver.payload.CreateUserRequest;
import com.financial.tools.recorderserver.payload.LoginRequest;
import com.financial.tools.recorderserver.payload.RegistrationRequest;
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
		CreateUserRequest request = new CreateUserRequest("userA", "1234", 100L, UserType.USER.getValue());

		// replay.
		userServiceClient.createUser(request);

		// verify.
		UserListResponse response = userServiceClient.listUsers();
		assertEquals(3, response.getUserList().size());
	}

	@Test
	public void testRegistrationAndLogin() {
		// prepare.
		String userName = "Kate";
		String password = "secret";
		RegistrationRequest registrationRequest = new RegistrationRequest(userName, password);

		// replay.
		String registerResult = userServiceClient.register(registrationRequest);

		// verify.
		assertEquals(userName, registerResult);

		// test Login.
		LoginRequest loginRequest = new LoginRequest(userName, password);
		String result = userServiceClient.login(loginRequest);

		// verify.
		assertNotNull(result);
		assertEquals(userName, result);
	}
}
