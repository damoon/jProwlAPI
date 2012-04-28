package net.sf.prowl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

public class NmaClient extends Client
{
	private String developerKey = null;

	public NmaClient(String apiKey, String application)
	{
		super(apiKey, application);
	}

	public void setDeveloperKey(String developerKey)
	{
		this.developerKey = developerKey;
	}

	protected Response getResponse(HttpURLConnection connection) throws ServiceException
	{
		InputStream httpStream;
		try
		{
			httpStream = connection.getInputStream();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpStream));

			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(line + "\n");
			}

			Pattern sqlFilePattern = Pattern.compile(" code=\"([^\\\"]+)\"");

			Matcher matcher = sqlFilePattern.matcher(stringBuilder.toString());

			Integer code = null;
			if (matcher.find())
			{
				code = Integer.valueOf(matcher.group(1).trim());
			}
			else
			{
				throw new ServiceException("the reponse code can not be dederment");
			}

			return new NmaResponse(code);

		}
		catch (IOException e)
		{
			throw new ServiceException(e);
		}
	}

	public void pushEvent(Event nmaEvent) throws ServiceException
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://www.notifymyandroid.com/publicapi/notify");

		prowlUrl.setParam("developerkey", this.developerKey);

		Response response = getResponse(prowlUrl, nmaEvent);
		if (response.getCode() != 200)
		{
			throw new ServiceException(response.getMessage());
		}
	}

	public boolean verifyApiKey()
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://www.notifymyandroid.com/publicapi/verify");
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
