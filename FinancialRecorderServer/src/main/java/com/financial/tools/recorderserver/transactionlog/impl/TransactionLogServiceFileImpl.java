package com.financial.tools.recorderserver.transactionlog.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.tools.recorderserver.transactionlog.TransactionLogService;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogEntry;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionLogThreadLocalContext;
import com.financial.tools.recorderserver.transactionlog.entry.TransactionResult;

public class TransactionLogServiceFileImpl implements TransactionLogService {

	private Logger logger = LoggerFactory.getLogger(TransactionLogServiceFileImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void init(String type) {
		TransactionLogThreadLocalContext.clear();
		TransactionLogEntry entry = new TransactionLogEntry();
		entry.setTransactionType(type);
		entry.setTransactionResult(TransactionResult.SUCCESSFUL);

		TransactionLogThreadLocalContext.setEntry(entry);
	}

	@Override
	public void setException(Throwable ex) {
		TransactionLogThreadLocalContext.getEntry().setTransactionResult(TransactionResult.FAIL);
	}

	@Override
	public void end() {
		TransactionLogEntry entry = TransactionLogThreadLocalContext.getEntry();
		if (entry != null) {
			try {
				String entryJsonString = mapper.writeValueAsString(entry);
				logger.info("Transaction log: " + entryJsonString);
			} catch (IOException e) {
				logger.error("convert transaction log entry to json failed.");
			} finally {
				TransactionLogThreadLocalContext.clear();
			}
		}
	}
}
