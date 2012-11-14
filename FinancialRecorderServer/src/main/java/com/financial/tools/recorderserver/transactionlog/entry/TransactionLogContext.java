package com.financial.tools.recorderserver.transactionlog.entry;

public class TransactionLogContext {

	private static final ThreadLocal<TransactionLogEntryBuilder> transactionLogEntryContext = new ThreadLocal<TransactionLogEntryBuilder>();

	private TransactionLogContext() {

	}

	public static TransactionLogEntryBuilder getEntry() {
		if (transactionLogEntryContext.get() == null) {
			transactionLogEntryContext.set(new TransactionLogEntryBuilder());
		}
		return transactionLogEntryContext.get();
	}

	public static void clear() {
		transactionLogEntryContext.remove();
	}

}
