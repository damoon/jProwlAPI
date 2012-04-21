package net.sourceforge.prowl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

public class ProwlClient
{

	private String apiKey;

	private String providerKey;

	private int connectionTimeout = 1000;

	private int readTimeout = 1000;

	private String application;
	
	public ProwlClient(String apiKey, String application)
	{
		this.apiKey = apiKey;
		this.application = application;
	}

	public void setProviderKey(String providerKey)
	{
		this.providerKey = providerKey;
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

	public void pushEvent(ProwlEvent prowlEvent) throws ProwlException
	{
		UrlBuilder prowlUrl = new UrlBuilder("add");

		prowlUrl.setParam("apikey", apiKey);
		prowlUrl.setParam("providerkey", this.providerKey);
		prowlUrl.setParam("application", application);
		prowlUrl.setParam("priority", String.valueOf(prowlEvent.getPriority()));
		prowlUrl.setParam("event", prowlEvent.getEvent());		
		prowlUrl.setParam("description", prowlEvent.getDescription());
		prowlUrl.setParam("url", prowlEvent.getUrl());

		Response response = getResponse(prowlUrl);
		if (response.getCode() != 200) {
			throw new ProwlException(response.getMessage());
		}
	}

	public boolean verifyApiKey() throws ProwlException
	{
		UrlBuilder prowlUrl = new UrlBuilder("verify");
		prowlUrl.setParam("apikey", apiKey);
		Response reponse = getResponse(prowlUrl);
		return reponse.getCode() == 200;
	}

	protected Response getResponse(UrlBuilder url) throws ProwlException
	{
		try
		{
			URL requestURL = new URL(url.getUrlAsString());
			HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
			connection.setConnectTimeout(connectionTimeout);
			connection.setReadTimeout(readTimeout);
			return new Response(connection.getResponseCode());
		}
		catch (IOException e)
		{
			throw new ProwlException(e);
		}
	}
}
