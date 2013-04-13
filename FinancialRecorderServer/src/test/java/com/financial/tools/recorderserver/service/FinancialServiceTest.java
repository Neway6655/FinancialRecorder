package com.financial.tools.recorderserver.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.financial.tools.recorderserver.AbstractComponentTestCase;
import com.financial.tools.recorderserver.client.FinancialServiceClient;
import com.financial.tools.recorderserver.entity.BudgetTrail;
import com.financial.tools.recorderserver.entity.BudgetTrailType;
import com.financial.tools.recorderserver.payload.AddFinancialRecordUsersRequest;
import com.financial.tools.recorderserver.payload.CashinRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordListResponse;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.financial.tools.recorderserver.payload.UserBudgetTrailResponse;
import com.financial.tools.recorderserver.payload.UserFinancialInfoResponse;
import com.google.common.collect.Lists;

public class FinancialServiceTest extends AbstractComponentTestCase {

	private FinancialServiceClient financialServiceClient;

	@Before
	public void setUp() {
		String serverAddress = baseUrl + "/api/";
		financialServiceClient = new FinancialServiceClient(serverAddress);
	}

	@Test
	public void testCashin() throws Exception {
		String userName = "Neway";
		CashinRequest request = new CashinRequest(userName, 100L);
		String result = financialServiceClient.cashin(request);
		assertEquals("200.0", result);

		// check budget trail.
		UserBudgetTrailResponse userBudgetTrailResponse = financialServiceClient.searchUserBudgetTrail(userName);
		assertNotNull(userBudgetTrailResponse);
		assertTrue(userBudgetTrailResponse.getBudgetTrailList().size() > 0);

		BudgetTrail budgetTrail = userBudgetTrailResponse.getBudgetTrailList().get(0);
		assertEquals(new Float(100).floatValue(), budgetTrail.getAmount(), 0.0);
		assertEquals(BudgetTrailType.CASH_IN.getValue(), budgetTrail.getType());
	}

	@Test
	public void testGetUserFinancialInfo() throws Exception {
		UserFinancialInfoResponse userFinancialInfo = financialServiceClient.getUserFinancialInfo("2");
		assertEquals("Fred", userFinancialInfo.getUserName());
		assertEquals((new Float(100)).floatValue(), userFinancialInfo.getBalance(), 0.0);
	}

	@Test
	public void testFinancialCalculateForUser() throws Exception {
		FinancialRecordRequest financialRecordRequest = new FinancialRecordRequest("ball", 20, Lists.newArrayList(
				"Neway", "Fred"), new Date());
		String financialRecordId = financialServiceClient.createFinancialRecord(financialRecordRequest);
		financialServiceClient.updateFinance(financialRecordId);

		// verify
		UserFinancialInfoResponse userFinancialInfo = financialServiceClient.getUserFinancialInfo("2");
		assertEquals((new Float(90)).floatValue(), userFinancialInfo.getBalance(), 0.0);

		// check budget trail.
		UserBudgetTrailResponse userBudgetTrailResponse = financialServiceClient.searchUserBudgetTrail("Fred");
		assertNotNull(userBudgetTrailResponse);
		assertTrue(userBudgetTrailResponse.getBudgetTrailList().size() > 0);

		BudgetTrail budgetTrail = userBudgetTrailResponse.getBudgetTrailList().get(0);
		assertEquals(new Float(10).floatValue(), budgetTrail.getAmount(), 0.0);
		assertEquals(BudgetTrailType.PAY_FEE.getValue(), budgetTrail.getType());
	}

	@Test
	public void testDeductFinancialRecordFee() {
		// prepare.
		FinancialRecordRequest financialRecordRequest = new FinancialRecordRequest("ballA", 20, Lists.newArrayList(
				"Neway", "Fred"), new Date());
		String financialRecordId = financialServiceClient.createFinancialRecord(financialRecordRequest);

		// replay.
		financialServiceClient.updateFinance(financialRecordId);

		// verify.
		UserFinancialInfoResponse newayInfo = financialServiceClient.getUserFinancialInfo("1");
		UserFinancialInfoResponse fredInfo = financialServiceClient.getUserFinancialInfo("2");
		assertEquals(90, newayInfo.getBalance(), 0.0);
		assertEquals(90, fredInfo.getBalance(), 0.0);
	}

	@Test
	public void testListFinancialRecords() throws ParseException {
		// prepare.
		FinancialRecordRequest financialRecordRequest = new FinancialRecordRequest("ballA", 20, Lists.newArrayList(
				"Neway", "Fred"), new Date());
		financialServiceClient.createFinancialRecord(financialRecordRequest);

		financialRecordRequest = new FinancialRecordRequest("ballB", 30, Lists.newArrayList("Neway", "Fred"),
				new Date());
		financialServiceClient.createFinancialRecord(financialRecordRequest);

		// replay.
		FinancialRecordListResponse response = financialServiceClient.listFinancialRecord();
		assertEquals(2, response.getRecordList().size());

		UserFinancialInfoResponse userAInfo = financialServiceClient.getUserFinancialInfo("1");
		UserFinancialInfoResponse userBInfo = financialServiceClient.getUserFinancialInfo("2");

		assertEquals(100, userAInfo.getBalance(), 0.0);
		assertEquals(100, userBInfo.getBalance(), 0.0);
	}

	@Test
	public void testAddUsers2FinancialRecord() {
		// prepare, create a financial record first.
		FinancialRecordRequest financialRecordRequest = new FinancialRecordRequest("ballA", 20,
				Lists.newArrayList("Fred"), new Date());
		String financialRecordId = financialServiceClient.createFinancialRecord(financialRecordRequest);

		// replay, add Neway to this financial record.
		AddFinancialRecordUsersRequest addFinancialRecordUsersRequest = new AddFinancialRecordUsersRequest();
		addFinancialRecordUsersRequest.setFinancialRecordId(Long.valueOf(financialRecordId));
		addFinancialRecordUsersRequest.setUserNameList(Lists.newArrayList("Neway"));

		financialServiceClient.addFinancialRecordUsers(addFinancialRecordUsersRequest);

		// verify.
		FinancialRecordResponse financialRecord = null;
		FinancialRecordListResponse financialRecordList = financialServiceClient.listFinancialRecord();
		for (FinancialRecordResponse response : financialRecordList.getRecordList()) {
			if (response.getId() == Long.valueOf(financialRecordId)) {
				financialRecord = response;
				break;
			}
		}

		assertNotNull(financialRecord);
		assertEquals(2, financialRecord.getUserNameList().size());
	}
}
