package com.financial.tools.recorderserver.transactionlog;

public interface TransactionLogService {

	void init(String type);

	void setException(Throwable ex);

	void end();

}
