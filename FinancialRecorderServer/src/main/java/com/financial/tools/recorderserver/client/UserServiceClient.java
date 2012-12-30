package com.financial.tools.recorderserver.client;

import javax.ws.rs.core.MediaType;

import com.financial.tools.recorderserver.payload.CreateUserRequest;
import com.financial.tools.recorderserver.payload.LoginRequest;
import com.financial.tools.recorderserver.payload.LoginResponse;
import com.financial.tools.recorderserver.payload.RegistrationRequest;
import com.financial.tools.recorderserver.payload.UserListResponse;

public class UserServiceClient extends AbstractFinancialClient {

	public UserServiceClient(String serverAddress) {
		super(serverAddress);
	}

	public String createUser(CreateUserRequest request) {
		client.path("user/create").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, request, String.class);
	}

	public UserListResponse listUsers() {
		client.path("user/list").accept(MediaType.APPLICATION_JSON);
		return get(client, UserListResponse.class);
	}

	public String register(RegistrationRequest request) {
		client.path("user/register").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, request, String.class);
	}

	public LoginResponse login(LoginRequest request) {
		client.path("user/login").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		return post(client, request, LoginResponse.class);
	}
}
