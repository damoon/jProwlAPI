package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProwlClientTest {

	@Test(expected=ProwlException.class)
	public void useWronApikey() throws ProwlException {
		ProwlClient client = new ProwlClient("brokenKey", "junittest");
		ProwlEvent event = new ProwlEvent("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.setProviderKey("providerKey");
		client.pushEvent(event);
	}

	@Test
	public void testSendMessage() throws ProwlException {
		ProwlClient client = new ProwlClient(System.getProperty("prowlApikey"), "junittest");
		ProwlEvent event = new ProwlEvent("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.pushEvent(event);
	}
	
	@Test
	public void testApiKeyVerification() throws ProwlException {
		ProwlClient client = new ProwlClient("brokenKey", "junit");
		assertFalse(client.verifyApiKey());
	}
}
