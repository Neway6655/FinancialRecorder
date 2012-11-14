package com.financial.tools.recorderserver.transactionlog.entry;

import java.util.List;

import com.financial.tools.recorderserver.transactionlog.TransactionType;

public class TransactionLogEntryBuilder {

	private TransactionType transactionType;

	private long financialRecordId;

	private String financialRecordName;

	private long amount;

	private long fee;

	private List<Long> userIdList;

	public TransactionLogEntryBuilder setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
		return this;
	}

	public TransactionLogEntryBuilder setFinancialRecordId(long financialRecordId) {
		this.financialRecordId = financialRecordId;
		return this;
	}

	public TransactionLogEntryBuilder setFinancialRecordName(String financialRecordName) {
		this.financialRecordName = financialRecordName;
		return this;
	}

	public TransactionLogEntryBuilder setAmount(long amount) {
		this.amount = amount;
		return this;
	}

	public TransactionLogEntryBuilder setFee(long fee) {
		this.fee = fee;
		return this;
	}

	public TransactionLogEntryBuilder setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
		return this;
	}

	public TransactionLogEntry build() {
		TransactionLogEntry entry = new TransactionLogEntry();
		entry.setTransactionType(transactionType);
		entry.setFinancialRecordId(financialRecordId);
		entry.setFinancialRecordName(financialRecordName);
		entry.setUserIdList(userIdList);
		entry.setAmount(amount);
		entry.setFee(fee);

		return entry;
	}
}
