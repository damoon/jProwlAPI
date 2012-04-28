package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServiceExceptionTest
{
	@Test
	public void test()
	{
		Exception exception = new Exception("abc test");
		ServiceException prowlException = new ServiceException(exception );
		assertSame("abc test", prowlException.getCause().getMessage());
	}

}
