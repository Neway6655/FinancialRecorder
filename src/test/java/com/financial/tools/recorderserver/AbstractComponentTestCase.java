package com.financial.tools.recorderserver;

import java.net.URL;
import java.sql.Driver;

import org.eclipse.jetty.server.Server;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.financial.tools.recorderserver.tools.jetty.JettyFactory;
import com.financial.tools.recorderserver.tools.util.DataLoader;
import com.financial.tools.recorderserver.tools.util.PropertiesLoader;

public abstract class AbstractComponentTestCase {

	protected static Server jettyServer;

	protected static SimpleDriverDataSource dataSource;

	protected static PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:/application.test.properties",
			"classpath:/application.test-local.properties");

	protected static String baseUrl;

	private static Logger logger = LoggerFactory.getLogger(AbstractComponentTestCase.class);

	@BeforeClass
	public static void beforeClass() throws Exception {
		baseUrl = propertiesLoader.getProperty("baseUrl");

		startJettyOnce();

		buildDataSourceOnce();
		reloadSampleData();
	}

	private static void startJettyOnce() throws Exception {
		if (jettyServer == null) {

			if (new URL(baseUrl).getHost().equals("localhost")) {

				System.setProperty("spring.profiles.active", "ct");

				jettyServer = JettyFactory.createServer(new URL(baseUrl).getPort(), RecorderServer.CONTEXT,
						"src/main/webapp");
				jettyServer.start();

				logger.info("Jetty Server started");
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected static void buildDataSourceOnce() throws ClassNotFoundException {
		if (dataSource == null) {
			dataSource = new SimpleDriverDataSource();
			dataSource.setDriverClass((Class<? extends Driver>) Class.forName(propertiesLoader
					.getProperty("jdbc.driver")));
			dataSource.setUrl(propertiesLoader.getProperty("jdbc.url"));
			dataSource.setUsername(propertiesLoader.getProperty("jdbc.username"));
			dataSource.setPassword(propertiesLoader.getProperty("jdbc.password"));
		}
	}

	protected static void reloadSampleData() throws Exception {
		DataLoader.reloadData(dataSource, "/data/sample-data.xml");
	}

}
