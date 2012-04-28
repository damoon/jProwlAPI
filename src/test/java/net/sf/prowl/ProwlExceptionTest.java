package net.sf.prowl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProwlExceptionTest
{
	@Test
	public void test()
	{
		Exception exception = new Exception("abc test");
		ProwlException prowlException = new ProwlException(exception );
		assertSame("abc test", prowlException.getCause().getMessage());
	}

}
