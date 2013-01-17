package com.financial.tools.recorderserver.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.financial.tools.recorderserver.entity.BudgetTrail;
import com.financial.tools.recorderserver.store.BudgetTrailStore;

public class BudgetTrailStoreRedisImpl implements BudgetTrailStore {

	private RedisTemplate<String, BudgetTrail> redisTemplate;

	@Override
	public void createBudgetTrail(BudgetTrail budgetTrail) {
		redisTemplate.opsForList().leftPush(budgetTrail.getUserName(), budgetTrail);
	}

	@Override
	public List<BudgetTrail> findBudgetTrailList(String userName) {
		long size = redisTemplate.boundListOps(userName).size();
		return redisTemplate.boundListOps(userName).range(0, size);
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, BudgetTrail> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
