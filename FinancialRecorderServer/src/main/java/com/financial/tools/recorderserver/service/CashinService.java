package com.financial.tools.recorderserver.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.financial.tools.recorderserver.payload.CashinRequest;

@Produces(MediaType.APPLICATION_JSON)
public class CashinService {

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
		return "";
	}
}
