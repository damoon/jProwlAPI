package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProwlClientTest {

	@Test(expected=ServiceException.class)
	public void useWrongApikey() throws ServiceException {
		ProwlClient client = new ProwlClient("brokenKey", "junittest");
		ProwlEvent event = new ProwlEvent("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.setProviderKey("providerKey");
		client.pushEvent(event);
	}
	
	@Test
	public void testWrongApiKeyVerification() throws ServiceException {
		ProwlClient client = new ProwlClient("brokenKey", "junit");
		assertFalse(client.verifyApiKey());
	}

	@Test
	public void testSendProwlMessage() throws ServiceException {
		ProwlClient client = new ProwlClient(System.getProperty("prowlApikey"), "junittest");
		ProwlEvent event = new ProwlEvent("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.pushEvent(event);
	}

	@Test
	public void testSendMessage() throws ServiceException {
		ProwlClient client = new ProwlClient(System.getProperty("prowlApikey"), "junittest");
		Event event = new Event("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.pushEvent(event);
	}
	
	@Test
	public void testValidApiKeyVerification() throws ServiceException {
		ProwlClient client = new ProwlClient(System.getProperty("prowlApikey"), "junit");
		assertTrue(client.verifyApiKey());
	}
}
