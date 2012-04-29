package net.sf.prowl;

import java.net.HttpURLConnection;

public class MockNotificationClient extends Client
{
	int getNumberOfEventsPushed = 0;

	public MockNotificationClient(String apiKey, String application)
	{
		super(apiKey, application);
	}

	@Override
	public boolean verifyApiKey()
	{
		return true;
	}

	@Override
	protected Response getResponse(HttpURLConnection connection) throws ServiceException
	{
		return null;
	}

	@Override
	public void pushEvent(Event event) throws ServiceException
	{
		getNumberOfEventsPushed++;
	}

	public Object getNumberOfEventsPushed()
	{
		return getNumberOfEventsPushed;
	}

}
