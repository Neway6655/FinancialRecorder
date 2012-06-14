package com.financial.tools.recorderserver.store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.financial.tools.recorderserver.entity.MemberGroup;

@Transactional
public class MemberGroupStore {

	private EntityManager entityManager;

	public MemberGroup getMemberGroup(String name) {
		return entityManager.find(MemberGroup.class, name);
	}

	public void saveMemberGroup(MemberGroup memberGroup) {
		entityManager.persist(memberGroup);
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
