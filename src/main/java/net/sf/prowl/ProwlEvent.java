package net.sf.prowl;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

public class ProwlEvent extends Event
{
	private String url;

	public ProwlEvent(String event, String description)
	{
		super(event, description);
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

}
