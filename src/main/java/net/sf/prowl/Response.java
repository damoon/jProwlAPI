package net.sf.prowl;

import java.util.HashMap;
import java.util.Map;

abstract class Response
{
	protected Integer code;

	protected Map<Integer, String> messages = new HashMap<Integer, String>();

	Response(Integer code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return messages.get(code);
	}
}
