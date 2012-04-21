package net.sourceforge.prowl;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

public class ProwlEvent
{
	private String description;

	private String event;

	private String url;

	private int priority = 0;

	public ProwlEvent(String event, String description)
	{
		this.event = event;
		this.description = description;
	}

	public void setPriority(int prio)
	{
		this.priority = prio;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getEvent()
	{
		return event;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
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
