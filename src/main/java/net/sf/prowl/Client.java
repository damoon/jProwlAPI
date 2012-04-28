package net.sf.prowl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

abstract class Client
{

	private String apikey;

	private String application;

	private int connectionTimeout = 1000;

	private int readTimeout = 1000;
	
	public Client(String apiKey, String application)
	{
		this.apikey = apiKey;
		this.application = application;
	}

	public void setTimeouts(int connectionTimeout, int readTimeout)
	{
		this.connectionTimeout = connectionTimeout;
		this.readTimeout = readTimeout;
	}

	public void setApplication(String application)
	{
		this.application = application;
	}

	abstract public boolean verifyApiKey();
	
	abstract protected Response getResponse (HttpURLConnection connection) throws ServiceException;
	
	abstract public void pushEvent (Event event) throws ServiceException;

	protected Response getResponse(UrlBuilder url, Event event) throws ServiceException
	{
		url.setParam("priority", String.valueOf(event.getPriority()));
		url.setParam("event", event.getEvent());
		url.setParam("description", event.getDescription());
		
		url.setParam("application", application);
		
		return getResponse(url);
	}
	
	protected Response getResponse(UrlBuilder url) throws ServiceException
	{
		url.setParam("apikey", apikey);
		return getResponse(url.getUrlAsString());
	}
	
	protected Response getResponse(String url) throws ServiceException
	{
		try
		{			
			URL requestURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
			connection.setConnectTimeout(connectionTimeout);
			connection.setReadTimeout(readTimeout);
			return getResponse(connection);
		}
		catch (IOException e)
		{
			throw new ServiceException(e);
		}
	}
}
