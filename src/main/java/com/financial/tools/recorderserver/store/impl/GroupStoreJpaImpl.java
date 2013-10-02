package com.financial.tools.recorderserver.store.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.financial.tools.recorderserver.entity.Group;
import com.financial.tools.recorderserver.store.GroupStore;

@Transactional
public class GroupStoreJpaImpl implements GroupStore {

	private EntityManager entityManager;

	@Override
	public Group getGroup(long id) {
		return entityManager.find(Group.class, id);
	}

	@Override
	public void saveGroup(Group group) {
		entityManager.persist(group);
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
