package net.sf.prowl;

class ProwlResponse extends Response
{
	public ProwlResponse(Integer code)
	{
		super(code);
		messages.put(400, "Bad request, the parameters you provided did not validate.");
		messages.put(401, "The API key given is not valid, and does not correspond to a user.");
		messages.put(405, "Method not allowed, you attempted to use a non-SSL connection to Prowl.");
		messages.put(406, "Your IP address has exceeded the API limit.");
		messages.put(500, "Internal server error, something failed to execute properly on the Prowl side.");
	}
}
