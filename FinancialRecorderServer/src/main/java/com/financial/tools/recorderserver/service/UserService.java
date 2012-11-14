package com.financial.tools.recorderserver.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.payload.CreateUserRequest;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.transactionlog.aop.TransactionLog;
import com.financial.tools.recorderserver.transactionlog.aop.TransactionLogType;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogEntry;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogThreadLocalContext;
import com.google.common.collect.Lists;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	private UserStore userStore;

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@TransactionLog(type = TransactionLogType.CREATE_USER)
	public String createUser(CreateUserRequest request) {
		TransactionLogEntry entry = TransactionLogThreadLocalContext.getEntry();

		User user = new User();
		user.setName(request.getName());
		user.setPassword(request.getPassword());
		long balance = request.getBalance();
		user.setBalance(balance);

		long userId = userStore.saveUser(user);
		entry.setUserIdList(Lists.newArrayList(userId)).setAmount(balance);

		return String.valueOf(userId);
	}

	@GET
	@Path("/list")
	public List<User> listUsers() {
		return userStore.findAll();
	}

	@Autowired
	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

}
