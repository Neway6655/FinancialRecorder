package com.financial.tools.recorderserver.payload;

import java.io.Serializable;
import java.util.List;

import com.financial.tools.recorderserver.entity.User;

public class UserListResponse implements Serializable {

	private static final long serialVersionUID = -5972285311070347386L;

	private List<User> userList;

	public UserListResponse() {
	}

	public UserListResponse(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
