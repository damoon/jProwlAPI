package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class NmaResponseTest
{

	@Test
	public void code200()
	{
		Response response = new NmaResponse(200);
		assertEquals(200, response.getCode());
	}

	@Test
	public void code400()
	{
		Response response = new NmaResponse(400);
		assertEquals(400, response.getCode());
		assertEquals("The data supplied is in the wrong format, invalid length or null.", response.getMessage());
	}

}
