package net.sf.prowl;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

public class ProwlClient extends Client
{
	private String providerKey = null;

	public ProwlClient(String apiKey, String application)
	{
		super(apiKey, application);
	}

	public void setProviderKey(String providerKey)
	{
		this.providerKey = providerKey;
	}

	protected Response getResponse(HttpURLConnection connection) throws ServiceException
	{
		try
		{
			return new ProwlResponse(connection.getResponseCode());
		}
		catch (IOException e)
		{
			throw new ServiceException(e);
		}
	}

	public void pushEvent(ProwlEvent prowlEvent) throws ServiceException
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://api.prowlapp.com/publicapi/add");

		prowlUrl.setParam("providerkey", this.providerKey);
		prowlUrl.setParam("url", prowlEvent.getUrl());

		Response response = getResponse(prowlUrl, (Event)prowlEvent);
		if (response.getCode() != 200)
		{
			throw new ServiceException(response.getMessage());
		}
	}

	@Override
	public void pushEvent(Event event) throws ServiceException
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://api.prowlapp.com/publicapi/add");

		prowlUrl.setParam("providerkey", this.providerKey);

		Response response = getResponse(prowlUrl, event);
		if (response.getCode() != 200)
		{
			throw new ServiceException(response.getMessage());
		}
	}

	public boolean verifyApiKey()
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://api.prowlapp.com/publicapi/verify");
		try
		{
			return getResponse(prowlUrl).getCode() == 200;
		}
		catch (ServiceException e)
		{
			return false;
		}
	}
}
