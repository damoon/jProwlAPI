package net.sf.prowl;

import static org.junit.Assert.*;

import org.apache.commons.codec.net.URLCodec;
import org.junit.Test;

public class UrlBuilderTest
{

	@Test(expected=ServiceException.class)
	public void throwException() throws ServiceException
	{
		UrlBuilder builder = new UrlBuilder("https://server.lan/command");
		builder.setParam("a", "b");
		builder.setStringEncoder(new URLCodec("MissingCharset"));
		assertEquals("https://server.lan/command", builder.getUrlAsString());
	}

	@Test
	public void emptyParams() throws ServiceException
	{
		UrlBuilder builder = new UrlBuilder("https://server.lan/command");
		assertEquals("https://server.lan/command", builder.getUrlAsString());
	}

	@Test
	public void oneParam() throws ServiceException
	{
		UrlBuilder builder = new UrlBuilder("https://server.lan/command");
		builder.setParam("key", "value");
		assertEquals("https://server.lan/command?key=value", builder.getUrlAsString());
	}
	
	@Test
	public void threeParam() throws ServiceException
	{
		UrlBuilder builder = new UrlBuilder("https://server.lan/command");
		builder.setParam("key1", "value1");
		builder.setParam("key2", "value2");
		builder.setParam("key3", "value3");
		assertEquals("https://server.lan/command?key3=value3&key2=value2&key1=value1", builder.getUrlAsString());
	}

}
