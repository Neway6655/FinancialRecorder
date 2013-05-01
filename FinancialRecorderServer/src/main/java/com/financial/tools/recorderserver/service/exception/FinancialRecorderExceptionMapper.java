package com.financial.tools.recorderserver.service.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.financial.tools.recorderserver.exception.FinancialRecorderException;

@Provider
public class FinancialRecorderExceptionMapper implements ExceptionMapper<FinancialRecorderException> {

	private Logger logger = LoggerFactory.getLogger(FinancialRecorderExceptionMapper.class);

	@Override
	public Response toResponse(FinancialRecorderException e) {
		logger.debug(e.getMessage(), e);
		return Response.status(e.getStatusCode()).type(MediaType.APPLICATION_JSON).entity(e.getErrorBody()).build();
	}

}
