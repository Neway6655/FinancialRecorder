package com.financial.tools.recorderserver.store.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.store.FinancialRecordStore;
import com.financial.tools.recorderserver.store.UserRecordStore;
import com.financial.tools.recorderserver.util.FinancialRecorderConstants;

@Transactional
public class FinancialRecordStoreJpaImpl implements FinancialRecordStore {

	private EntityManager entityManager;

	private UserRecordStore userRecordStore;

	@Override
	public long createFinancialRecord(FinancialRecord financialRecord) {
		entityManager.persist(financialRecord);
		for (String userName : financialRecord.getUserNames().split(FinancialRecorderConstants.USER_NAME_SEPARATE)) {
			userRecordStore.addRecord2User(userName, financialRecord.getId());
		}
		return financialRecord.getId();
	}

	@Override
	public FinancialRecord getFinancialRecord(long financialRecordId) {
		Query query = entityManager.createQuery("SELECT f FROM FinancialRecord f where f.status = 1 and f.id=:id",
				FinancialRecord.class);
		query.setParameter("id", financialRecordId);
		try {
			FinancialRecord result = (FinancialRecord) query.getSingleResult();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public FinancialRecord updateFinancialRecord(FinancialRecord financialRecord) {
		entityManager.clear();
		entityManager.merge(financialRecord);

		return financialRecord;
	}

	@Override
	public List<FinancialRecord> listFinancialRecords() {
		Query query = entityManager.createQuery("SELECT f FROM FinancialRecord f", FinancialRecord.class);
		query.setMaxResults(10);
		return query.getResultList();
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	public void setUserRecordStore(UserRecordStore userRecordStore) {
		this.userRecordStore = userRecordStore;
	}

}
