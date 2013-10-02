package com.financial.tools.recorderserver.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtils {

	private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

	public static byte[] md5Digest(String rawValue) {
		MessageDigest md5Digest;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
			byte[] firstRoundMd5DigestValue = md5Digest.digest(rawValue.getBytes());
			byte[] secondRoundMd5DigestValue = md5Digest.digest(firstRoundMd5DigestValue);
			return secondRoundMd5DigestValue;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Digest algorithm not exists.", e);
			return null;
		}
	}
}
