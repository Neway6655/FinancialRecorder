package com.financial.tools.recorderserver.store;

import com.financial.tools.recorderserver.entity.User;

public interface UserStore {

	public User getUser(long userId);

	public void updateBalance(long userId, long balance);
}
