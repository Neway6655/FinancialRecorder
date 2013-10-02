/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.financial.tools.recorderserver.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.ext.oracle.OracleConnection;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public class DataLoader {

	private static Logger logger = LoggerFactory.getLogger(DataLoader.class);
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static void reloadData(DataSource dataSource, String... xmlFilePaths) throws Exception {
		execute(DatabaseOperation.CLEAN_INSERT, dataSource, xmlFilePaths);
	}

	private static void execute(DatabaseOperation operation, DataSource dataSource, String... xmlFilePaths)
			throws DatabaseUnitException, SQLException {
		IDatabaseConnection connection = getConnection(dataSource);
		try {
			for (String xmlPath : xmlFilePaths) {
				try {
					InputStream input = resourceLoader.getResource(xmlPath).getInputStream();
					IDataSet dataSet = new FlatXmlDataSetBuilder().setColumnSensing(true).build(input);
					operation.execute(connection, dataSet);
				} catch (IOException e) {
					logger.warn(xmlPath + " file not found", e);
				}
			}
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	protected static IDatabaseConnection getConnection(DataSource dataSource) throws DatabaseUnitException,
			SQLException {
		Connection connection = dataSource.getConnection();
		String url = connection.getMetaData().getURL();
		if (StringUtils.contains(url, ":h2:")) {
			return new H2Connection(connection, null);
		} else if (StringUtils.contains(url, ":mysql:")) {
			return new MySqlConnection(connection, null);
		} else if (StringUtils.contains(url, ":oracle:")) {
			return new OracleConnection(connection, null);
		} else {
			return new DatabaseConnection(connection);
		}
	}
}
