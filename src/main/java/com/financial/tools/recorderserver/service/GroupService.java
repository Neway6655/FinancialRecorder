package com.financial.tools.recorderserver.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.financial.tools.recorderserver.payload.CreateGroupRequest;
import com.financial.tools.recorderserver.store.GroupStore;

@Path("/group")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupService {

	private GroupStore groupStore;

	@POST
	@Path("/create")
	public String createGroup(CreateGroupRequest request) {
		groupStore.saveGroup(request.getGroup());
		return "";
	}
}
