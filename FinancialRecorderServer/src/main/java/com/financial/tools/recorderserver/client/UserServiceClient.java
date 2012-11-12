package com.financial.tools.recorderserver.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.CreateUserRequest;

public class UserServiceClient extends AbstractFinancialClient {

	public UserServiceClient(String serverAddress) {
		super(serverAddress);
	}

	public String createUser(CreateUserRequest request) {
		client.path("user/create").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, request, String.class);
	}

	public List<User> listUsers() {
		client.path("user/list").accept(MediaType.APPLICATION_JSON);
		return getList(client, User.class);
	}
}
