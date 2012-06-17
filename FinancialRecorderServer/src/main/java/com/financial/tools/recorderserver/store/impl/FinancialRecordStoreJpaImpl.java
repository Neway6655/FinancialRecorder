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
	public void createFinancialRecord(FinancialRecord financialRecord) {
		entityManager.persist(financialRecord);
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
