package com.financial.tools.recorderserver.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMappingUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonMappingUtils.class);
	private static ObjectMapper mapper = new ObjectMapper();

	public static String getJsonStringWithoutExceptionOut(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("Error to serialize object to Json string.", e);
		}
		return "";
	}
}
