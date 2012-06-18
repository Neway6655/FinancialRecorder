package com.financial.tools.recorderserver.store.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.store.FinancialRecordStore;

@Transactional
public class FinancialRecordStoreJpaImpl implements FinancialRecordStore {

	private EntityManager entityManager;

	@Override
	public long createFinancialRecord(FinancialRecord financialRecord) {
		entityManager.persist(financialRecord);
		return financialRecord.getId();
	}

	@Override
	public FinancialRecord getFinancialRecord(long financialRecordId) {
		return entityManager.find(FinancialRecord.class, financialRecordId);
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
