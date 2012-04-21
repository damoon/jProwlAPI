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

	@Test(expected=IllegalArgumentException.class)
	public void testNull() throws Exception {
		client.pushEvent(null);		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testEmptyEvent() throws Exception {
		client.pushEvent(new DefaultProwlEvent());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPriority() throws Exception {
		ProwlEvent prowlEvent = new DefaultProwlEvent("", "", "", "", 10);
		client.pushEvent(prowlEvent);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullArguments() throws Exception {
		ProwlEvent prowlEvent = new DefaultProwlEvent("", null, "", "", 0);
		client.pushEvent(prowlEvent);		
	}
	
	@Test
	public void testSuccess() throws Exception {
		ProwlEvent prowlEvent = new DefaultProwlEvent("key", "app", "event", "desc", 2);
		String url = client.pushEvent(prowlEvent);
		assertTrue(url.contains("description=desc"));
		assertTrue(url.contains("event=event"));
		assertTrue(url.contains("application=app"));
		assertTrue(url.contains("apikey=key"));
		assertTrue(url.contains("priority=2"));
		assertSame("https://api.prowlapp.com/publicapi/add?description=desc&event=event&application=app&apikey=key&priority=2".length(),
				url.length());
	}
}
