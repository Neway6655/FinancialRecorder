package com.financial.tools.recorderserver.payload;

import com.financial.tools.recorderserver.entity.Group;

public class CreateGroupRequest {

	private Group group;

	public CreateGroupRequest() {
	}

	public CreateGroupRequest(Group group) {
		this.group = group;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
