package com.financial.tools.recorderserver.tools.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyFactory {

	public static Server createServer(int port, String contextPath, String webApp) {
		Server jettyServer = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setReuseAddress(false);
		jettyServer.setConnectors(new Connector[] { connector });
		jettyServer.setHandler(new WebAppContext(webApp, contextPath));
		jettyServer.setStopAtShutdown(true);
		return jettyServer;
	}
}
