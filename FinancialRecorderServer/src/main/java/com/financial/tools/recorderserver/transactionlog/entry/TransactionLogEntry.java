package com.financial.tools.recorderserver.transactionlog.entry;

import java.io.Serializable;
import java.util.List;

public class TransactionLogEntry implements Serializable {

	private static final Long serialVersionUID = -8324440347502312758L;

	private TransactionResult transactionResult;

	private String transactionType;

	private Long financialRecordId;

	private String financialRecordName;

	private Long amount;

	private Long fee;

	private List<String> userNameList;

	public TransactionLogEntry() {
	}

	public TransactionResult getTransactionResult() {
		return transactionResult;
	}

	public TransactionLogEntry setTransactionResult(TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
		return this;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public TransactionLogEntry setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		return this;
	}

	public Long getFinancialRecordId() {
		return financialRecordId;
	}

	public TransactionLogEntry setFinancialRecordId(Long financialRecordId) {
		this.financialRecordId = financialRecordId;
		return this;
	}

	public String getFinancialRecordName() {
		return financialRecordName;
	}

	public TransactionLogEntry setFinancialRecordName(String financialRecordName) {
		this.financialRecordName = financialRecordName;
		return this;
	}

	public Long getAmount() {
		return amount;
	}

	public TransactionLogEntry setAmount(Long amount) {
		this.amount = amount;
		return this;
	}

	public Long getFee() {
		return fee;
	}

	public TransactionLogEntry setFee(Long fee) {
		this.fee = fee;
		return this;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

}
