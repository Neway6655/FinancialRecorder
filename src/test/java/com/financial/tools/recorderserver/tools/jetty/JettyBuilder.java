package com.financial.tools.recorderserver.tools.jetty;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author ejjgzzu
 * 
 */
public class JettyBuilder {

	private int port;

	private List<WebAppContext> webAppContextList = new ArrayList<WebAppContext>();

	public JettyBuilder setPort(int port) {
		this.port = port;
		return this;
	}

	public JettyBuilder addWebAppContext(String contextPath, String webApp) {
		webAppContextList.add(new WebAppContext(webApp, contextPath));
		return this;
	}

	public Server build() {
		Server jettyServer = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(this.port);
		connector.setReuseAddress(false);
		jettyServer.setConnectors(new Connector[] { connector });

		HandlerCollection handlercollection = new HandlerCollection();
		handlercollection.setHandlers(webAppContextList.toArray(new WebAppContext[] {}));

		jettyServer.setHandler(handlercollection);
		jettyServer.setStopAtShutdown(true);

		return jettyServer;
	}
}
