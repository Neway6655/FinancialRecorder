package com.financial.tools.recorderserver.store;

import com.financial.tools.recorderserver.entity.Group;

public interface GroupStore {

	public Group getGroup(long id);

	public void saveGroup(Group group);
}
