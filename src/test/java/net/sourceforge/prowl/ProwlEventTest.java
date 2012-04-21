package net.sourceforge.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProwlEventTest
{
	@Test
	public void basics()
	{
		ProwlEvent event = new ProwlEvent("testEvent", "testDescription");
		assertEquals("testEvent", event.getEvent());
		assertEquals("testDescription", event.getDescription());
	}
	
	@Test
	public void changeBasics()
	{
		ProwlEvent event = new ProwlEvent("testEvent", "testDescription");
		assertEquals("testEvent", event.getEvent());
		assertEquals("testDescription", event.getDescription());
		event.setEvent("newTestEvent");
		assertEquals("newTestEvent", event.getEvent());
		event.setDescription("newTestDescription");
		assertEquals("newTestDescription", event.getDescription());
	}
	
	@Test
	public void url()
	{
		ProwlEvent event = new ProwlEvent("testEvent", "testDescription");
		event.setUrl("testUrl");
		assertEquals("testUrl", event.getUrl());
	}
	
	@Test
	public void priority()
	{
		ProwlEvent event = new ProwlEvent("testEvent", "testDescription");
		event.setPriority(-1);
		assertEquals(-1, event.getPriority());
	}

}
