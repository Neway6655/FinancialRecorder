package com.financial.tools.recorderserver.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.entity.MemberGroup;
import com.financial.tools.recorderserver.payload.CashinRequest;
import com.financial.tools.recorderserver.store.MemberGroupStore;

@Produces(MediaType.APPLICATION_JSON)
public class CashinService {

	private MemberGroupStore memberGroupStore;

	@POST
	@Path("/cashin")
	@Produces(MediaType.TEXT_PLAIN)
	public String cashin(CashinRequest request) {
		System.out.println("demo cashin operation.");
		return "";
	}

	@GET
	@Path("/activityinfo")
	public String getActivityInfo(@QueryParam("userid") String userId) {
		System.out.println(userId);
		MemberGroup memberGroup = new MemberGroup("HelloWorld");
		memberGroupStore.saveMemberGroup(memberGroup);
		MemberGroup result = memberGroupStore.getMemberGroup("HelloWorld");
		if (result != null) {
			System.out.println("member group name: " + memberGroup.getName());
		} else {
			System.out.println("member group not found.");
		}
		return "";
	}

	@Autowired
	public void setMemberGroupStore(MemberGroupStore memberGroupStore) {
		this.memberGroupStore = memberGroupStore;
	}
}
