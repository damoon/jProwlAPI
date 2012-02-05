package net.sourceforge.prowl.api;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * <p>
 * 
 * This enum lists all available prowl parameters with their corresponding
 * api pendant.
 */
public enum ProwlParameter {

	apikey("apikey")
	, application("application") 
	, event("event")
	, description("description")
	, priority("priority")
	, url("url")
	, providerkey("providerkey");
	
	private String apiMapping;
	
	ProwlParameter(String apiMapping) {
		this.apiMapping = apiMapping;
	}
	
	public String getApiMapping() {
		return apiMapping;
	}
}
