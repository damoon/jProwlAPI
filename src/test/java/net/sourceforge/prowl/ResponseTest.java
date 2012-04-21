package net.sourceforge.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResponseTest
{

	@Test
	public void code200()
	{
		Response response = new Response(200);
		assertEquals(200, response.getCode());
	}

	@Test
	public void code400()
	{
		Response response = new Response(400);
		assertEquals(400, response.getCode());
		assertEquals("Bad request, the parameters you provided did not validate.", response.getMessage());
	}

}
