package net.sourceforge.prowl.url;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	private ProwlCommand prowlCommand;
	private Map<ProwlParameter, String> paramMap = new HashMap<ProwlParameter, String>();
	
	/**
	 * Default private constructor.
	 */
	private DefaultProwlURLBuilder() {
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
			this.prowlCommand = command;
		}
		return this;
	}
	
	public ProwlURLBuilder appendParam(ProwlParameter param, String value) {
		if(param != null && value != null) {
			paramMap.put(param, value);
		}
		return this;
	}
	
	public String getURL() {
		StringBuilder url = new StringBuilder();
		url.append(PROWL_API_URL);
		
		if(prowlCommand != null) {
			url.append(prowlCommand.getApiMapping());
		}
		appendParameters(url);
		
		return url.toString();
	}

	private void appendParameters(StringBuilder url) {
		if(!paramMap.isEmpty()) {
			url.append("?");

			boolean isFirst = true;
			Iterator<ProwlParameter> iter = paramMap.keySet().iterator();
			while(iter.hasNext()) {
				ProwlParameter key = iter.next();
				String value = paramMap.get(key);
				
				if(!isFirst) {
					url.append("&");
				}
				
				url.append(key);
				url.append("=");
				url.append(value);
				isFirst = false;
			}
		}
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
