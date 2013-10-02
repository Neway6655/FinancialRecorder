package com.financial.tools.recorderserver.transactionlog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.financial.tools.recorderserver.transactionlog.TransactionLogService;

@Aspect
public class TransactionLogAspect {

	private TransactionLogService transactonLogService;

	@Around("@annotation(transactionLog)")
	public Object around(ProceedingJoinPoint pjp, TransactionLog transactionLog) throws Throwable {
		String type = transactionLog.type();
		transactonLogService.init(type);
		try {
			return pjp.proceed();
		} catch (Throwable ex) {
			transactonLogService.setException(ex);
			throw ex;
		} finally {
			transactonLogService.end();
		}
	}

	@Autowired
	public void setTransactonLogService(TransactionLogService transactonLogService) {
		this.transactonLogService = transactonLogService;
	}
}
