package net.sourceforge.prowl.test;

import junit.framework.Assert;

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
		Assert.assertEquals(PROWL_API_URL + "add", result);
	}
	
	@Test
	public void testAddParam() {
		String result = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.add).appendParam(ProwlParameter.apikey, "123").getURL();
		Assert.assertEquals(PROWL_API_URL + "add?apikey=123", result);
	}
	
	@Test
	public void testAddMultipleParams() {
		ProwlURLBuilder urlBuilder = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.add);
		urlBuilder.appendParam(ProwlParameter.apikey, "123");
		urlBuilder.appendParam(ProwlParameter.application, "myApplication");
		urlBuilder.appendParam(ProwlParameter.description, "myDescription");
		Assert.assertEquals(PROWL_API_URL+"add?application=myApplication&apikey=123&description=myDescription", urlBuilder.getURL());
	}
	
	@Test
	public void testChangeProwlUrl() {
		DefaultProwlURLBuilder.setProwlApiUrl("http://anotherUrl/");
		
		String result = DefaultProwlURLBuilder.createUrl().getURL();
		Assert.assertEquals("http://anotherUrl/", result);
		
		DefaultProwlURLBuilder.setProwlApiUrl(PROWL_API_URL);
	}
	
}
