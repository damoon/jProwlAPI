package net.sf.prowl;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

class Event
{
	protected String description;

	protected String event;

	protected int priority = 0;

	public Event(String event, String description)
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

}
