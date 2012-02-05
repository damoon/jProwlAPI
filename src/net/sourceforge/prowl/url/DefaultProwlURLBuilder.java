package net.sourceforge.prowl.url;

import net.sourceforge.prowl.api.ProwlCommand;
import net.sourceforge.prowl.api.ProwlParameter;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * 
 * The default implementation of {@link ProwlURLBuilder}.
 * @see ProwlURLBuilder
 */
public class DefaultProwlURLBuilder implements ProwlURLBuilder {

	private static String PROWL_API_URL = "https://api.prowlapp.com/publicapi/";
	private String url;
	
	/**
	 * Default private constructor.
	 */
	private DefaultProwlURLBuilder() {
		this.url = PROWL_API_URL;
	}
	
	/**
	 * Retrieves a new instance of {@link ProwlURLBuilder}.
	 * 
	 * @return
	 */
	public static ProwlURLBuilder createUrl() {
		return new DefaultProwlURLBuilder();
	}
	
	public ProwlURLBuilder useCommand(ProwlCommand command) {
		if(command != null) {
			url += command.getApiMapping();
		}
		return this;
	}
	
	public ProwlURLBuilder appendParam(ProwlParameter param, String value) {
		if(param != null && value != null) {
			url += "?" + param;
			url += "=" + value;
			url += "&";
		}
		return this;
	}
	
	public String getURL() {
		if(url.endsWith("&")) {
			return url.substring(0, url.length()-1);
		}
		return url;
	}
	
	/**
	 * Sets the prowl api url.
	 * 
	 * @param prowlApiUrl the new prowl api url.
	 */
	public static void setProwlApiUrl(String prowlApiUrl) {
		PROWL_API_URL = prowlApiUrl;
	}
	
	/**
	 * Retrieves the currently set prowl api url.
	 * 
	 * @return the currently set prowl api url
	 */
	public static String getProwlApiUrl() {
		return PROWL_API_URL;
	}
	
	@Override
	public String toString() {
		return getURL();
	}
}
