package net.sourceforge.prowl.test;

import junit.framework.Assert;

import org.junit.Test;

import net.sourceforge.prowl.api.DefaultProwlEvent;
import net.sourceforge.prowl.api.ProwlClient;
import net.sourceforge.prowl.api.ProwlEvent;
import net.sourceforge.prowl.exception.ProwlException;

public class RealWorldTest {

	@Test
	public void testSendMessage() {
		ProwlClient c = new ProwlClient();
		ProwlEvent e = new DefaultProwlEvent(
				"myKey", "application", "event",
				"message", 0, "customLaunchUrl");
		String message;
		try {
			message = c.pushEvent(e);
			System.out.println(message);
		} catch (ProwlException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testApiKeyVerification() {
		ProwlClient c = new ProwlClient();
		try {
			boolean isKeyValid = c.verifyApiKey(null);
			Assert.assertEquals(false, isKeyValid);
		} catch (ProwlException e) {
			e.printStackTrace();
		}
	}
}
