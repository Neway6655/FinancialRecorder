package com.financial.tools.recorderserver.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.business.DeviceManager;
import com.financial.tools.recorderserver.exception.ErrorCode;
import com.financial.tools.recorderserver.exception.FinancialRecorderException;
import com.financial.tools.recorderserver.payload.DeviceRegistrationRequest;

@Path("/device")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class DeviceService {

	private DeviceManager deviceManager;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/register")
	public String register(DeviceRegistrationRequest request) {
		if (StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getDeviceRegId())) {
			throw new FinancialRecorderException(ErrorCode.MISSING_PARAMETER_ERROR,
					"userName and registerId can not be empty.");
		}
		deviceManager.register(request.getUserName(), request.getDeviceRegId());
		return "";
	}

	@GET
	@Path("/getRegId")
	public String getRegisteredId(@QueryParam("userName") String userName) {
		if (StringUtils.isEmpty(userName)) {
			throw new FinancialRecorderException(ErrorCode.MISSING_PARAMETER_ERROR, "userName can not be empty.");
		}
		return deviceManager.getRegisteredDeviceId(userName);
	}

	@Autowired
	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

}
