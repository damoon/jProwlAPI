package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class NmaClientTest {

	@Test(expected=GatewayException.class)
	public void useWrongApikey() throws GatewayException {
		NmaClient client = new NmaClient("brokenKey", "junittest");
		Event event = new Event("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.setDeveloperKey("developerKey");
		client.pushEvent(event);
	}
	
	@Test
	public void testApiKeyVerification() throws GatewayException {
		NmaClient client = new NmaClient("brokenKey", "junit");
		assertFalse(client.verifyApiKey());
	}

	@Test
	public void testSendMessage() throws GatewayException {
		NmaClient client = new NmaClient(System.getProperty("nmaApikey"), "junittest");
		Event event = new Event("test event", "test description");
		client.setTimeouts(2000, 2000);
		client.setApplication("application");
		client.pushEvent(event);
	}
	
	@Test
	public void testValidApiKeyVerification() throws GatewayException {
		NmaClient client = new NmaClient(System.getProperty("nmaApikey"), "junit");
		assertTrue(client.verifyApiKey());
	}
}
