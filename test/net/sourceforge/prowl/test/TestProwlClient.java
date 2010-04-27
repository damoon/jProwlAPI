package net.sourceforge.prowl.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sourceforge.prowl.api.DefaultProwlEvent;
import net.sourceforge.prowl.api.ProwlClient;
import net.sourceforge.prowl.api.ProwlEvent;
import net.sourceforge.prowl.exception.ProwlException;


public class TestProwlClient {

	private static ProwlClient client;
	
	@BeforeClass
	public static void setUp() {
		class MockProwlClient extends ProwlClient {
			@Override
			protected String sendPushNotification(String url)
					throws ProwlException {
				return url;
			}
		}
		
		client = new MockProwlClient();
	}
	
	@Test
	public void testNull() throws Exception {
		try {
			client.pushEvent(null);
			fail();
		}
		catch(IllegalArgumentException e) {
			//good
		}
		
	}
	
	@Test
	public void testEmptyEvent() throws Exception {
		try {
			client.pushEvent(new DefaultProwlEvent());
			fail();
		}
		catch(IllegalArgumentException e) {
			//good
		}
	}
	
	@Test
	public void testPriority() throws Exception {
		try {
			ProwlEvent prowlEvent = new DefaultProwlEvent("", "", "", "", 10);
			client.pushEvent(prowlEvent);
			fail();
		}
		catch(IllegalArgumentException e) {
			//good
		}
	}
	
	@Test
	public void testNullArguments() throws Exception {
		try {
			ProwlEvent prowlEvent = new DefaultProwlEvent("", null, "", "", 0);
			client.pushEvent(prowlEvent);
			fail();
		}
		catch(IllegalArgumentException e) {
			//good
		}
		
	}
	
	@Test
	public void testSuccess() throws Exception {
		ProwlEvent prowlEvent = new DefaultProwlEvent("key", "app", "event", "desc", 2);
		String url = client.pushEvent(prowlEvent);
		assertEquals("https://prowl.weks.net/publicapi/add?apikey=key&application=app&event=event&description=desc&priority=2",
				url);
	}
}
