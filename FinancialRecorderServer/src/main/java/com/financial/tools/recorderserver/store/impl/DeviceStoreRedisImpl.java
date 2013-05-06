package com.financial.tools.recorderserver.store.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.financial.tools.recorderserver.store.DeviceStore;

/**
 * DeviceStore is response for relationship between user and device, currently
 * we only keep one device for one user.
 * 
 * @author eortwyz
 * 
 */
public class DeviceStoreRedisImpl implements DeviceStore {

	private static final String DEVICEREG_KEY = "DR_";

	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void registerDevice(String userName, String deviceRegId) {
		String key = getKey(userName);
		redisTemplate.boundValueOps(key).set(deviceRegId, 15, TimeUnit.DAYS);
	}

	@Override
	public String getDeviceRegId(String userName) {
		String key = getKey(userName);
		return redisTemplate.boundValueOps(key).get();
	}

	@Override
	public void unRegisterDevice(String userName, String deviceRegId) {
		String key = getKey(userName);
		redisTemplate.boundValueOps(key).set(null);
	}

	private String getKey(String userName) {
		return new StringBuilder().append(DEVICEREG_KEY).append(userName).toString();
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
