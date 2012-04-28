package net.sf.prowl;

public class ServiceException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ServiceException(Exception e)
	{
		super(e);
	}

	public ServiceException(String e)
	{
		super(e);
	}
}
