package net.sf.prowl;

import java.net.HttpURLConnection;

public class MockNotificationClient extends Client
{
	private static int getNumberOfEventsPushed = 0;

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
	protected Response getResponse(HttpURLConnection connection) throws GatewayException
	{
		return null;
	}

	@Override
	public void pushEvent(Event event) throws GatewayException
	{
		getNumberOfEventsPushed++;
	}
	
	public static int getNumberOfEventsPushed()
	{
		return getNumberOfEventsPushed;
	}
	
	public static void resetNumberOfEventsPushed()
	{
		getNumberOfEventsPushed = 0;
	}

}
