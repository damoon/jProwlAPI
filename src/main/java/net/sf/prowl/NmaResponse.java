package net.sf.prowl;

class NmaResponse extends Response
{
	public NmaResponse(Integer code)
	{
		super(code);
		messages.put(200, "Notification submitted.");
		messages.put(400, "The data supplied is in the wrong format, invalid length or null.");
		messages.put(401, "None of the API keys provided were valid.");
		messages.put(402, "Maximum number of API calls per hour exceeded.");
		messages.put(500, "Internal server error. Please contact our support if the problem persists.");
	}
}
