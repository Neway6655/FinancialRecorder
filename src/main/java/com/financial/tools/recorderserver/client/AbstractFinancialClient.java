package com.financial.tools.recorderserver.client;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.ServerWebApplicationException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.form.Form;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.Lists;

public abstract class AbstractFinancialClient {

	protected final WebClient client;

	public interface ClientCallback {
		Response invoke();
	}

	public AbstractFinancialClient(String serverAddress) {
		client = WebClient.create(serverAddress, Lists.newArrayList(new JacksonJaxbJsonProvider()), true);
		WebClient.getConfig(client).getHttpConduit().getClient().setAllowChunking(false);
	}

	protected <T> T get(WebClient client, Class<T> responseClass) {
		try {
			return client.get(responseClass);
		} catch (ServerWebApplicationException e) {
			// TODO: add handler.
			throw e;
		} finally {
			client.reset();
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(WebClient client, Class<T> responseClass) {
		try {
			return (List<T>) client.getCollection(responseClass);
		} catch (ServerWebApplicationException e) {
			// TODO: add handler.
			throw e;
		} finally {
			client.reset();
		}
	}

	protected <T> T post(WebClient client, Object body, Class<T> responseClass) {
		try {
			return client.post(body, responseClass);
		} catch (ServerWebApplicationException e) {
			// TODO: add handler.
			throw e;
		} finally {
			client.reset();
		}
	}

	/**
	 * post a request as application/x-www-form-urlencoded type.
	 */
	protected <T> T postForm(WebClient client, Map<String, Object> requestMap, Class<T> responseClass) {
		Object body = convertRequestMap2Form(requestMap);
		return post(client, body, responseClass);
	}

	private Form convertRequestMap2Form(Map<String, Object> requestMap) {
		Form form = new Form();
		for (Entry<String, Object> entry : requestMap.entrySet()) {
			if (entry.getValue() != null) {
				form.set(entry.getKey(), entry.getValue());
			}
		}
		return form;
	}
}
