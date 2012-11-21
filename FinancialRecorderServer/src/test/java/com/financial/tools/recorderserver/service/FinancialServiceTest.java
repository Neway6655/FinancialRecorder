package com.financial.tools.recorderserver.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.financial.tools.recorderserver.AbstractComponentTestCase;
import com.financial.tools.recorderserver.client.FinancialServiceClient;
import com.financial.tools.recorderserver.payload.CashinRequest;
import com.financial.tools.recorderserver.payload.FinancialRecordListResponse;
import com.financial.tools.recorderserver.payload.FinancialRecordRequest;
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
		CashinRequest request = new CashinRequest(1L, 100L);
		String result = financialServiceClient.cashin(request);
		assertEquals("200", result);
	}

	@Test
	public void testGetUserFinancialInfo() throws Exception {
		UserFinancialInfoResponse userFinancialInfo = financialServiceClient.getUserFinancialInfo("2");
		assertEquals("Fred", userFinancialInfo.getUserName());
		assertEquals(100L, userFinancialInfo.getBalance());
	}

	@Test
	public void testFinancialCalculateForUser() throws Exception {
		FinancialRecordRequest financialRecordRequest = new FinancialRecordRequest("ball", 20, Lists.newArrayList(1L,
				2L));
		String financialRecordId = financialServiceClient.createFinancialRecord(financialRecordRequest);
		financialServiceClient.updateFinance(financialRecordId);

		// verify
		UserFinancialInfoResponse userFinancialInfo = financialServiceClient.getUserFinancialInfo("2");
		assertEquals(90L, userFinancialInfo.getBalance());
	}

	@Test
	public void testListFinancialRecords() {
		// prepare.
		FinancialRecordRequest financialRecordRequest = new FinancialRecordRequest("ballA", 20, Lists.newArrayList(1L,
				2L));
		financialServiceClient.createFinancialRecord(financialRecordRequest);

		financialRecordRequest = new FinancialRecordRequest("ballB", 30, Lists.newArrayList(1L, 2L));
		financialServiceClient.createFinancialRecord(financialRecordRequest);

		// replay.
		FinancialRecordListResponse response = financialServiceClient.listFinancialRecord();
		assertEquals(2, response.getRecordList().size());

		UserFinancialInfoResponse userAInfo = financialServiceClient.getUserFinancialInfo("1");
		UserFinancialInfoResponse userBInfo = financialServiceClient.getUserFinancialInfo("2");

		assertEquals(75, userAInfo.getBalance());
		assertEquals(75, userBInfo.getBalance());
	}

}
