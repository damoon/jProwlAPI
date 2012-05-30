package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class GatewayExceptionTest
{
	@Test
	public void test()
	{
		Exception exception = new Exception("abc test");
		GatewayException prowlException = new GatewayException(exception );
		assertSame("abc test", prowlException.getCause().getMessage());
	}

}
