package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProwlResponseTest
{

	@Test
	public void code200()
	{
		Response response = new ProwlResponse(200);
		assertEquals(200, response.getCode());
	}

	@Test
	public void code400()
	{
		Response response = new ProwlResponse(400);
		assertEquals(400, response.getCode());
		assertEquals("Bad request, the parameters you provided did not validate.", response.getMessage());
	}

}
