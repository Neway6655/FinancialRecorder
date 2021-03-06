package com.financial.tools.recorderserver.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.financial.tools.recorderserver.entity.BudgetTrail;
import com.financial.tools.recorderserver.store.BudgetTrailStore;

public class BudgetTrailStoreRedisImpl implements BudgetTrailStore {

	private static final String BUDGETTRAIL_KEY = "BT_";

	private RedisTemplate<String, BudgetTrail> redisTemplate;

	@Override
	public void createBudgetTrail(BudgetTrail budgetTrail) {
		String key = getKey(budgetTrail.getUserName());
		redisTemplate.opsForList().leftPush(key, budgetTrail);
	}

	@Override
	public List<BudgetTrail> findBudgetTrailList(String userName) {
		String key = getKey(userName);
		long size = redisTemplate.boundListOps(key).size();
		return redisTemplate.boundListOps(key).range(0, size);
	}

	private String getKey(String key) {
		return new StringBuilder().append(BUDGETTRAIL_KEY).append(key).toString();
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, BudgetTrail> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
