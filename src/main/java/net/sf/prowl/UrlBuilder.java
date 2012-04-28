package net.sf.prowl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

public class UrlBuilder
{

	private static String baseUrl = "https://api.prowlapp.com/publicapi/";
	
	private String prowlCommand;
	
	private Map<String, String> paramMap = new HashMap<String, String>();

	private URLCodec codec = new URLCodec("UTF-8");

	public UrlBuilder(String prowlCommand)
	{
		this.prowlCommand = prowlCommand;
	}

	public UrlBuilder setStringEncoder(URLCodec codec)
	{
		this.codec = codec;
		return this;
	}

	public UrlBuilder setParam(String param, String value)
	{
		if (param != null && value != null)
		{
			paramMap.put(param, value);
		}
		return this;
	}

	public String getUrlAsString() throws ProwlException
	{
		StringBuilder url = new StringBuilder();

		url.append(baseUrl);

		url.append(prowlCommand);

		if (!paramMap.isEmpty())
		{
			url.append("?");

			boolean isFirst = true;
			for (Entry<String, String> entry : paramMap.entrySet())
			{
				if (!isFirst)
				{
					url.append("&");
				}
				try
				{
					url.append(codec.encode(entry.getKey()));
					url.append("=");
					url.append(codec.encode(entry.getValue()));
				}
				catch (EncoderException e)
				{
					throw new ProwlException(e);
				}
				isFirst = false;
			}
		}

		return url.toString();
	}
}
