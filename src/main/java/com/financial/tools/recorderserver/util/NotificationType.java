package com.financial.tools.recorderserver.util;

public enum NotificationType {

	JOIN_ACTIVITY("Join Activity", "join_activity"), CASH_IN("Cash In", "cash_in"), DEDUCT_FEE("Deduct Fee",
			"deduct_fee"), REMINDER("Reminder", "reminder");

	private String title;

	private String type;

	private NotificationType(String title, String type) {
		this.title = title;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

}
