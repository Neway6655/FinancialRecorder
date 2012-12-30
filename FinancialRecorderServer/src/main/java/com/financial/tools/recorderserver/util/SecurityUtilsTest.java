package com.financial.tools.recorderserver.util;

import junit.framework.Assert;

import org.junit.Test;

public class SecurityUtilsTest {

	@Test
	public void testMd5Digest() {
		String rawValue = "rawPassword";
		String md5Digest = SecurityUtils.md5Digest(rawValue);
		System.out.println(md5Digest);

		String expectedValue = new String(SecurityUtils.md5Digest("rawPassword"));
		Assert.assertEquals(expectedValue, md5Digest);

	}

}
