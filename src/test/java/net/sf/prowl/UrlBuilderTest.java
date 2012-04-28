package net.sf.prowl;

import static org.junit.Assert.*;

import org.apache.commons.codec.net.URLCodec;
import org.junit.Test;

public class UrlBuilderTest
{

	@Test(expected=ProwlException.class)
	public void throwException() throws ProwlException
	{
		UrlBuilder builder = new UrlBuilder("command");
		builder.setParam("a", "b");
		builder.setStringEncoder(new URLCodec("MissingCharset"));
		assertEquals("https://api.prowlapp.com/publicapi/command", builder.getUrlAsString());
	}

	@Test
	public void emptyParams() throws ProwlException
	{
		UrlBuilder builder = new UrlBuilder("command");
		assertEquals("https://api.prowlapp.com/publicapi/command", builder.getUrlAsString());
	}

	@Test
	public void oneParam() throws ProwlException
	{
		UrlBuilder builder = new UrlBuilder("command");
		builder.setParam("key", "value");
		assertEquals("https://api.prowlapp.com/publicapi/command?key=value", builder.getUrlAsString());
	}
	
	@Test
	public void threeParam() throws ProwlException
	{
		UrlBuilder builder = new UrlBuilder("command");
		builder.setParam("key1", "value1");
		builder.setParam("key2", "value2");
		builder.setParam("key3", "value3");
		assertEquals("https://api.prowlapp.com/publicapi/command?key3=value3&key2=value2&key1=value1", builder.getUrlAsString());
	}

}
