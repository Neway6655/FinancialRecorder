package com.financial.tools.recorderserver.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.business.FinancialManager;
import com.financial.tools.recorderserver.payload.CashinRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordListResponse;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.financial.tools.recorderserver.transactionlog.aop.TransactionLog;
import com.financial.tools.recorderserver.transactionlog.aop.TransactionLogType;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogEntry;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogThreadLocalContext;
import com.google.common.collect.Lists;

@Path("/finance")
@Produces(MediaType.APPLICATION_JSON)
public class FinancialService {

	private FinancialManager financialManager;

	@POST
	@Path("/cashin")
	@Produces(MediaType.TEXT_PLAIN)
	@TransactionLog(type = TransactionLogType.CASH_IN)
	public String cashin(CashinRequest request) {
		TransactionLogEntry entry = TransactionLogThreadLocalContext.getEntry();

		long balance = financialManager.updateUserBalance(request.getUserId(), request.getAmount());

		entry.setAmount(request.getAmount()).setUserIdList(Lists.newArrayList(request.getUserId()));

		return String.valueOf(balance);
	}

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@TransactionLog(type = TransactionLogType.CREATE_FINANCIAL_RECORD)
	public Response createFinancialRecord(FinancialRecordRequest financialRecordRequest) {
		TransactionLogEntry entry = TransactionLogThreadLocalContext.getEntry();

		long financialRecordId = financialManager.createFinancialRecord(financialRecordRequest);

		financialManager.updateFinance(financialRecordId);

		entry.setFinancialRecordId(financialRecordId).setFinancialRecordName(financialRecordRequest.getName())
				.setFee(financialRecordRequest.getTotalFee()).setUserIdList(financialRecordRequest.getUserIdList());

		return Response.ok(financialRecordId).build();
	}

	@GET
	@Path("/list")
	public FinancialRecordListResponse listFinancialRecords() {
		return new FinancialRecordListResponse(financialManager.listFinancialRecords());
	}

	@GET
	@Path("/update/{financialRecord}")
	@Produces(MediaType.TEXT_PLAIN)
	@TransactionLog(type = TransactionLogType.UPDATE_FINANCIAL_RECORD)
	public String updateFinance(@PathParam("financialRecord") String financialRecordId) {
		TransactionLogEntry entry = TransactionLogThreadLocalContext.getEntry();

		long financialRecordIdValue = Long.valueOf(financialRecordId);
		financialManager.updateFinance(financialRecordIdValue);

		entry.setFinancialRecordId(financialRecordIdValue);
		return "";
	}

	@GET
	@Path("/info/{userId}")
	public UserFinancialInfoResponse getUserFinancialInfo(@PathParam("userId") String userId) {
		return financialManager.getUserFinancialInfo(Long.valueOf(userId));
	}

	@Autowired
	public void setFinancialManager(FinancialManager financialManager) {
		this.financialManager = financialManager;
	}

}
