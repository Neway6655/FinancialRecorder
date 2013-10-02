package com.financial.tools.recorderserver.util;

import java.util.List;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.payload.FinancialRecordResponse;
import com.google.common.collect.Lists;

public class CopyUtils {

	public static FinancialRecordResponse convertFinancialRecord2Response(FinancialRecord financialRecord) {
		FinancialRecordResponse financialRecordResponse = new FinancialRecordResponse();
		financialRecordResponse.setId(financialRecord.getId());
		financialRecordResponse.setName(financialRecord.getName());
		financialRecordResponse.setTotalFee(financialRecord.getTotalFee());
		financialRecordResponse.setRecordDate(financialRecord.getRecordDate());

		List<String> userNameList = Lists.newArrayList();
		for (String name : financialRecord.getUserNames().split(",")) {
			userNameList.add(name);
		}
		financialRecordResponse.setUserNameList(userNameList);

		return financialRecordResponse;
	}
}
