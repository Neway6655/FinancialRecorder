package com.financial.tools.recorderserver.transactionlog.entry;

import java.io.Serializable;
import java.util.List;

import com.financial.tools.recorderserver.transactionlog.TransactionType;

public class TransactionLogEntry implements Serializable {

	private static final long serialVersionUID = -8324440347502312758L;

	private TransactionType transactionType;

	private long financialRecordId;

	private String financialRecordName;

	private long amount;

	private long fee;

	private List<Long> userIdList;

	public TransactionLogEntry() {
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public long getFinancialRecordId() {
		return financialRecordId;
	}

	public void setFinancialRecordId(long financialRecordId) {
		this.financialRecordId = financialRecordId;
	}

	public String getFinancialRecordName() {
		return financialRecordName;
	}

	public void setFinancialRecordName(String financialRecordName) {
		this.financialRecordName = financialRecordName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public List<Long> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}

}
