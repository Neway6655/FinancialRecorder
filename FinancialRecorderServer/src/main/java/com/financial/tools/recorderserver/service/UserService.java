package com.financial.tools.recorderserver.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.CreateUserRequest;
import com.financial.tools.recorderserver.store.UserStore;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	private UserStore userStore;

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	public String createUser(CreateUserRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setPassword(request.getPassword());
		user.setBalance(request.getBalance());

		long userId = userStore.saveUser(user);
		return String.valueOf(userId);
	}

	@Autowired
	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

}
