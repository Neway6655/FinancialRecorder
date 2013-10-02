package com.financial.tools.recorderserver.util;

import org.junit.Assert;
import org.junit.Test;

public class SecurityUtilsTest {

	@Test
	public void testMd5Digest() {
		String rawValue = "rawPassword";
		byte[] md5Digest = SecurityUtils.md5Digest(rawValue);
		System.out.println(md5Digest);

		byte[] expectedValue = SecurityUtils.md5Digest("rawPassword");
		Assert.assertArrayEquals(expectedValue, md5Digest);

	}

}
