package net.sf.prowl;

public class GatewayException extends Exception
{
	private static final long serialVersionUID = 1L;

	public GatewayException(Exception e)
	{
		super(e);
	}

	public GatewayException(String e)
	{
		super(e);
	}
}
