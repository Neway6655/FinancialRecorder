package com.financial.tools.recorderserver.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.business.FinancialManager;
import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.entity.User;
import com.financial.tools.recorderserver.entity.UserType;
import com.financial.tools.recorderserver.exception.ErrorCode;
import com.financial.tools.recorderserver.exception.FinancialRecorderException;
import com.financial.tools.recorderserver.payload.ChangePasswordRequest;
import com.financial.tools.recorderserver.payload.CreateUserRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.LoginRequest;
import com.financial.tools.recorderserver.payload.RegistrationRequest;
import com.financial.tools.recorderserver.payload.UserListResponse;
import com.financial.tools.recorderserver.payload.UserRecordsResponse;
import com.financial.tools.recorderserver.store.UserRecordStore;
import com.financial.tools.recorderserver.store.UserStore;
import com.financial.tools.recorderserver.transactionlog.aop.TransactionLog;
import com.financial.tools.recorderserver.transactionlog.aop.TransactionLogType;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogEntry;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogThreadLocalContext;
import com.financial.tools.recorderserver.util.CopyUtils;
import com.financial.tools.recorderserver.util.SecurityUtils;
import com.google.common.collect.Lists;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	private UserStore userStore;

	private UserRecordStore userRecordStore;

	private FinancialManager financialManager;

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@TransactionLog(type = TransactionLogType.CREATE_USER)
	public String createUser(CreateUserRequest request) {
		TransactionLogEntry entry = TransactionLogThreadLocalContext.getEntry();
		User user = new User();
		user.setName(request.getName());
		user.setPassword(SecurityUtils.md5Digest(request.getPassword()));
		long balance = request.getBalance();
		user.setBalance(balance);
		user.setType(request.getType());

		long userId = userStore.saveUser(user);
		entry.setUserNameList(Lists.newArrayList(user.getName()));
		entry.setAmount(balance);

		return String.valueOf(userId);
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(LoginRequest request) {
		String userName = request.getUserName();
		logger.debug("user login request: {}", userName);

		User user = userStore.getUserByName(userName);

		if (user == null) {
			throw new FinancialRecorderException(ErrorCode.LOGIN_ERROR, "User name not exists.");
		}

		String rawPassword = request.getPassword();
		byte[] encryptedPassword = SecurityUtils.md5Digest(rawPassword);
		if (Arrays.equals(user.getPassword(), encryptedPassword)) {
			return user.getName();
		} else {
			logger.debug("user's password: {} is not matched with request password: {}.", user.getPassword(),
					encryptedPassword);
			throw new FinancialRecorderException(ErrorCode.LOGIN_ERROR, "Password not correct.");
		}
	}

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public String register(RegistrationRequest request) {
		String userName = request.getUserName();

		User userResult = userStore.getUserByName(userName);
		if (userResult != null) {
			throw new FinancialRecorderException(ErrorCode.LOGIN_ERROR,
					"User name already used, please try another one.");
		}

		byte[] password = SecurityUtils.md5Digest(request.getPassword());
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		user.setType(UserType.USER.getValue());
		userStore.saveUser(user);

		return userName;
	}

	@POST
	@Path("/changePassword")
	@Produces(MediaType.TEXT_PLAIN)
	public String changePassword(ChangePasswordRequest request) {
		String userName = request.getUserName();
		User user = userStore.getUserByName(userName);
		if (user == null) {
			throw new FinancialRecorderException(ErrorCode.USER_NOT_EXIST_ERROR, "User with name: " + userName
					+ " does not exist.");
		}
		byte[] md5DigestOldPassword = SecurityUtils.md5Digest(request.getOldPassword());
		if (!Arrays.equals(user.getPassword(), md5DigestOldPassword)) {
			throw new FinancialRecorderException(ErrorCode.PASSWORD_INVALID_ERROR, "Old password is not valid.");
		}
		user.setPassword(SecurityUtils.md5Digest(request.getNewPassword()));
		long userId = userStore.saveUser(user);

		return String.valueOf(userId);
	}

	@GET
	@Path("/list")
	public UserListResponse listUsers() {
		return new UserListResponse(userStore.findAll());
	}

	@GET
	@Path("/search")
	public UserRecordsResponse searchRecords(@QueryParam("userName") String userName) {
		UserRecordsResponse response = new UserRecordsResponse();
		List<FinancialRecordResponse> financialRecordResponseList = Lists.newArrayList();
		List<Long> financialRecordIdList = userRecordStore.findFinancialRecordIdList(userName);
		// TODO: Neway, need to add cache for this method.
		for (Long financialRecordId : financialRecordIdList) {
			FinancialRecord financialRecord = financialManager.getFinancialRecord(financialRecordId);
			FinancialRecordResponse financialRecordResponse = CopyUtils
					.convertFinancialRecord2Response(financialRecord);
			financialRecordResponseList.add(financialRecordResponse);
		}
		response.setRecordList(financialRecordResponseList);

		return response;
	}

	@Autowired
	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

	@Autowired
	public void setUserRecordStore(UserRecordStore userRecordStore) {
		this.userRecordStore = userRecordStore;
	}

}
