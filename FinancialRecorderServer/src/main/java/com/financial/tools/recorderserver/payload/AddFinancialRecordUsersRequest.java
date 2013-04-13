package com.financial.tools.recorderserver.payload;

import java.io.Serializable;
import java.util.List;

public class AddFinancialRecordUsersRequest implements Serializable {

	private static final long serialVersionUID = 151685108360136742L;

	private long financialRecordId;

	private List<String> userNameList;

	public AddFinancialRecordUsersRequest() {
	}

	public long getFinancialRecordId() {
		return financialRecordId;
	}

	public void setFinancialRecordId(long financialRecordId) {
		this.financialRecordId = financialRecordId;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

}
