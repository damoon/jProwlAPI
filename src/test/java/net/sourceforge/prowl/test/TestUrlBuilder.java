package net.sourceforge.prowl.test;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sourceforge.prowl.api.ProwlCommand;
import net.sourceforge.prowl.api.ProwlParameter;
import net.sourceforge.prowl.url.DefaultProwlURLBuilder;
import net.sourceforge.prowl.url.ProwlURLBuilder;

public class TestUrlBuilder {

	private static final String PROWL_API_URL = "https://api.prowlapp.com/publicapi/";
	
	@Test
	public void testUseCommand() {
		String result = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.add).getURL();
		assertEquals(PROWL_API_URL + "add", result);
	}
	
	@Test
	public void testAddParam() {
		String result = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.add).appendParam(ProwlParameter.apikey, "123").getURL();
		assertEquals(PROWL_API_URL + "add?apikey=123", result);
	}
	
	@Test
	public void testAddMultipleParams() {
		ProwlURLBuilder urlBuilder = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.add);
		
		urlBuilder.appendParam(ProwlParameter.apikey, "123");
		urlBuilder.appendParam(ProwlParameter.application, "myApplication");
		urlBuilder.appendParam(ProwlParameter.description, "myDescription");
		String url = urlBuilder.getURL();
	
		assertTrue(url.contains("description=myDescription"));
		assertTrue(url.contains("application=myApplication"));
		assertTrue(url.contains("apikey=123"));
		assertEquals("https://api.prowlapp.com/publicapi/add?apikey=123&description=myDescription&application=myApplication".length(), url.length());
	}
	
	@Test
	public void testChangeProwlUrl() {
		DefaultProwlURLBuilder.setProwlApiUrl("http://anotherUrl/");
		
		String result = DefaultProwlURLBuilder.createUrl().getURL();
		assertEquals("http://anotherUrl/", result);
		
		DefaultProwlURLBuilder.setProwlApiUrl(PROWL_API_URL);
	}
	
}
