package com.financial.tools.recorderserver;

import java.net.URL;

import org.eclipse.jetty.server.Server;

import com.financial.tools.recorderserver.tools.jetty.JettyFactory;
import com.financial.tools.recorderserver.tools.util.PropertiesLoader;

public class RecorderServer {
	public static final String CONTEXT = "/recorder-server";

	public static void main(String[] args) throws Exception {

		PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:/application.test.properties",
				"classpath:/application.test-local.properties");
		String baseUrl = propertiesLoader.getProperty("baseUrl");

		System.setProperty("spring.profiles.active", "dev");
		Server jettyServer = JettyFactory.createServer(new URL(baseUrl).getPort(), CONTEXT, "src/main/webapp");
		try {
			jettyServer.start();
			System.out.println("Recorder Server started successfully! URL: " + baseUrl);
		} catch (Exception e) {
			System.exit(-1);
		}
	}
}
