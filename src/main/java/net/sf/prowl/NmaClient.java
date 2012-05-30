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

	protected Response getResponse(HttpURLConnection connection) throws GatewayException
	{
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			InputStream httpStream = connection.getInputStream();
			try
			{
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpStream));
				try 
				{
					String line;
					while ((line = bufferedReader.readLine()) != null)
					{
						stringBuilder.append(line + "\n");
					}
				}
				catch (IOException e)
				{
					throw new GatewayException(e);
				}
				finally
				{
					bufferedReader.close();
				}
			}
			catch (IOException e)
			{
				throw new GatewayException(e);
			}
			finally
			{
				httpStream.close();
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
				throw new GatewayException("the reponse code can not be dederment");
			}
			
			return new NmaResponse(code);
		}
		catch (IOException e)
		{
			throw new GatewayException(e);
		}
	}

	public void pushEvent(Event nmaEvent) throws GatewayException
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://www.notifymyandroid.com/publicapi/notify");

		prowlUrl.setParam("developerkey", this.developerKey);

		Response response = getResponse(prowlUrl, nmaEvent);
		if (response.getCode() != 200)
		{
			throw new GatewayException(response.getMessage());
		}
	}

	public boolean verifyApiKey()
	{
		UrlBuilder prowlUrl = new UrlBuilder("https://www.notifymyandroid.com/publicapi/verify");
		try
		{
			return getResponse(prowlUrl).getCode() == 200;
		}
		catch (GatewayException e)
		{
			return false;
		}
	}
}
