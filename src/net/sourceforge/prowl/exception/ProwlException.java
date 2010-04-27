package net.sourceforge.prowl.exception;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * <p>
 * 
 * This exception class holds the information what went wrong if an prowl request fails.
 *
 */
public class ProwlException extends Exception {

	private int responseCode;
	
	/**
	 * Constructs a new ProwlException with a response code and a message.
	 * 
	 * @param code the response code
	 * @param cause the response message
	 */
	public ProwlException(int code, String cause) {
		super(cause);
		this.responseCode = code;
	}
	
	/**
	 * Retrieves the response code from an request.
	 * 
	 * @return the response code, e.g. 401
	 */
	public int getResponseCode() {
		return responseCode;
	}
	
	/**
	 * Sets the response code for an request.
	 * 
	 * @param code the response code
	 */
	public void setResponseCode(int code) {
		responseCode = code;
	}
	
	
}
