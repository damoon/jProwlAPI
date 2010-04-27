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
		client = new ProwlClient();
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
	public void testApiKey() throws Exception {
		try {
			ProwlEvent prowlEvent = new DefaultProwlEvent("", "", "", "", 0);
			client.pushEvent(prowlEvent);
			fail();
		}
		catch(ProwlException e) {
			//good
		}
	}
}
