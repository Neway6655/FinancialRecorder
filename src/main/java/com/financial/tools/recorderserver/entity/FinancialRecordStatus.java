package com.financial.tools.recorderserver.entity;

public enum FinancialRecordStatus {
	NEW(1), UPDATED(2);

	private int value;

	private FinancialRecordStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static FinancialRecordStatus getStatusByValue(int value) {
		for (FinancialRecordStatus financialRecordStatus : FinancialRecordStatus.values()) {
			if (financialRecordStatus.getValue() == value) {
				return financialRecordStatus;
			}
		}
		return null;
	}
}
