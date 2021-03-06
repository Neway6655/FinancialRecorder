package com.financial.tools.recorderserver.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.financial.tools.recorderserver.store.UserRecordStore;

public class UserRecordStoreRedisImpl implements UserRecordStore {

	private static final String USERRECORD_KEY = "UR_";

	private RedisTemplate<String, Long> redisTemplate;

	@Override
	public void addRecord2User(String userName, long financialRecordId) {
		String key = getKey(userName);
		redisTemplate.opsForList().leftPush(key, financialRecordId);
	}

	@Override
	public List<Long> findFinancialRecordIdList(String userName) {
		String key = getKey(userName);
		long size = redisTemplate.boundListOps(key).size();
		return redisTemplate.boundListOps(key).range(0, size);
	}

	private String getKey(String key) {
		return new StringBuilder().append(USERRECORD_KEY).append(key).toString();
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, Long> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
