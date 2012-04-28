package net.sf.prowl;

public class ProwlException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ProwlException(Exception e)
	{
		super(e);
	}

	public ProwlException(String e)
	{
		super(e);
	}
}
