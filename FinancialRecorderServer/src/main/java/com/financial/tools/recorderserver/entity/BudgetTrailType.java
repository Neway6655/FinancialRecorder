package com.financial.tools.recorderserver.entity;

public enum BudgetTrailType {

	PAY_FEE("Pay for fee"), CASH_IN("Cash in");

	private String value;

	private BudgetTrailType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
