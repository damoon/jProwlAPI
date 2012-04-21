package net.sourceforge.prowl.test;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sourceforge.prowl.api.DefaultProwlEvent;
import net.sourceforge.prowl.api.ProwlClient;
import net.sourceforge.prowl.api.ProwlEvent;
import net.sourceforge.prowl.exception.ProwlException;

public class RealWorldTest {

	@Test(expected=ProwlException.class)
	public void testSendMessage() throws ProwlException {
		ProwlClient c = new ProwlClient();
		ProwlEvent e = new DefaultProwlEvent(
				"myKey", "application", "event",
				"message", 0, "customLaunchUrl");
		c.pushEvent(e);
	}
	
	@Test
	public void testApiKeyVerification() {
		ProwlClient c = new ProwlClient();
		try {
			boolean isKeyValid = c.verifyApiKey(null);
			assertFalse(isKeyValid);
		} catch (ProwlException e) {
			e.printStackTrace();
		}
	}
}
