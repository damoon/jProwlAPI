package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
	@Test
	public void basics()
	{
		Event event = new Event("testEvent", "testDescription");
		assertEquals("testEvent", event.getEvent());
		assertEquals("testDescription", event.getDescription());
	}
	
	@Test
	public void changeBasics()
	{
		Event event = new Event("testEvent", "testDescription");
		assertEquals("testEvent", event.getEvent());
		assertEquals("testDescription", event.getDescription());
		event.setEvent("newTestEvent");
		assertEquals("newTestEvent", event.getEvent());
		event.setDescription("newTestDescription");
		assertEquals("newTestDescription", event.getDescription());
	}
	
	@Test
	public void priority()
	{
		Event event = new Event("testEvent", "testDescription");
		event.setPriority(-1);
		assertEquals(-1, event.getPriority());
	}

}
