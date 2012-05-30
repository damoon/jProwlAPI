package net.sf.prowl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

class UrlBuilder
{
	private String baseUrl;
	
	private Map<String, String> paramMap = new HashMap<String, String>();

	private URLCodec codec = new URLCodec("UTF-8");

	public UrlBuilder(String baseUrl)
	{
		this.baseUrl = baseUrl;
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

	public String getUrlAsString() throws GatewayException
	{
		StringBuilder url = new StringBuilder();

		url.append(baseUrl);

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
					throw new GatewayException(e);
				}
				isFirst = false;
			}
		}

		return url.toString();
	}
}
