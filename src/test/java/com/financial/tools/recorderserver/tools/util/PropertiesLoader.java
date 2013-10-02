package com.financial.tools.recorderserver.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PropertiesLoader {

	private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private final Properties properties;

	public PropertiesLoader(String... resourcesPaths) {
		properties = loadProperties(resourcesPaths);
	}

	public Properties getProperties() {
		return properties;
	}

	public String getProperty(String key) {
		String result = getValue(key);

		if (result != null) {
			return result;
		} else {
			throw new NoSuchElementException();
		}
	}

	public String getProperty(String key, String defaultValue) {
		String result = getValue(key);
		if (result != null) {
			return result;
		} else {
			return defaultValue;
		}
	}

	public Integer getInteger(String key) {
		String value = getValue(key);
		if (value != null) {
			return Integer.valueOf(value);
		} else {
			throw new NoSuchElementException();
		}
	}

	public Integer getInteger(String key, Integer defaultValue) {
		String value = getValue(key);
		if (value != null) {
			return Integer.valueOf(value);
		} else {
			return defaultValue;
		}
	}

	public Double getDouble(String key) {
		String value = getValue(key);
		if (value != null) {
			return Double.valueOf(value);
		} else {
			throw new NoSuchElementException();
		}
	}

	public Double getDouble(String key, Double defaultValue) {
		String value = getValue(key);
		if (value != null) {
			return Double.valueOf(value);
		} else {
			return defaultValue;
		}
	}

	public Boolean getBoolean(String key) {
		String value = getValue(key);
		if (value != null) {
			return Boolean.valueOf(value);
		} else {
			throw new NoSuchElementException();
		}
	}

	public Boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);
		if (value != null) {
			return Boolean.valueOf(value);
		} else {
			return defaultValue;
		}
	}

	private String getValue(String key) {
		String result = System.getProperty(key);
		if (result != null) {
			return result;
		}
		return properties.getProperty(key);
	}

	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {

			logger.debug("Loading properties file from:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
				logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException ioe) {
					// ignore
				}
			}
		}
		return props;
	}

}
