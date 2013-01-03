package com.financial.tools.recorderserver.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.financial.tools.recorderserver.entity.FinancialRecord;
import com.financial.tools.recorderserver.store.UserRecordStore;

public class UserRecordStoreRedisImpl implements UserRecordStore {

	private RedisTemplate<String, FinancialRecord> redisTemplate;

	@Override
	public void addRecord2User(String userName, FinancialRecord record) {
		redisTemplate.opsForList().leftPush(userName, record);
	}

	@Override
	public List<FinancialRecord> findFinancialRecordList(String userName) {
		long size = redisTemplate.boundListOps(userName).size();
		return redisTemplate.boundListOps(userName).range(0, size);
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, FinancialRecord> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
