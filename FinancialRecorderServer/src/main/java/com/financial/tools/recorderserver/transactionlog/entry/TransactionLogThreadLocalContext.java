package com.financial.tools.recorderserver.transactionlog.entry;

public class TransactionLogThreadLocalContext {

	private static final ThreadLocal<TransactionLogEntry> transactionLogEntryContext = new ThreadLocal<TransactionLogEntry>();

	private TransactionLogThreadLocalContext() {
	}

	public static TransactionLogEntry getEntry() {
		if (transactionLogEntryContext.get() == null) {
			return null;
		}
		return transactionLogEntryContext.get();
	}

	public static void setEntry(TransactionLogEntry transactionLogEntry) {
		transactionLogEntryContext.set(transactionLogEntry);
	}

	public static void clear() {
		transactionLogEntryContext.set(null);
	}

}
