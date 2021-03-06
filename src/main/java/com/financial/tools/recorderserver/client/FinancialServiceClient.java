package com.financial.tools.recorderserver.client;

import javax.ws.rs.core.MediaType;

import com.financial.tools.recorderserver.payload.AddFinancialRecordUsersRequest;
import com.financial.tools.recorderserver.payload.CashinRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordListResponse;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.UpdateFinanceRequest;
import com.financial.tools.recorderserver.payload.UserBudgetTrailResponse;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;

public class FinancialServiceClient extends AbstractFinancialClient {

	public FinancialServiceClient(String serverAddress) {
		super(serverAddress);
	}

	public String cashin(CashinRequest request) {
		client.path("finance/cashin").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, request, String.class);
	}

	public String createFinancialRecord(FinancialRecordRequest financialRecordRequest) {
		client.path("/finance/create").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, financialRecordRequest, String.class);
	}

	public FinancialRecordListResponse listFinancialRecord(int financialRecordStatus) {
		client.path("finance/list/{financialRecordStatus}", financialRecordStatus).accept(MediaType.APPLICATION_JSON);
		return get(client, FinancialRecordListResponse.class);
	}

	public String updateFinance(UpdateFinanceRequest updateFinanceRequest) {
		client.path("finance/update").type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN);
		return post(client, updateFinanceRequest, String.class);
	}

	public FinancialRecordResponse addFinancialRecordUsers(AddFinancialRecordUsersRequest request) {
		client.path("finance/addUsers").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		return post(client, request, FinancialRecordResponse.class);
	}

	public UserFinancialInfoResponse getUserFinancialInfo(String userId) {
		client.path("finance/info/{userId}", userId).accept(MediaType.APPLICATION_JSON);
		return get(client, UserFinancialInfoResponse.class);
	}

	public UserBudgetTrailResponse searchUserBudgetTrail(String userName) {
		client.path("finance/search").query("userName", userName).accept(MediaType.APPLICATION_JSON);
		return get(client, UserBudgetTrailResponse.class);
	}
}
