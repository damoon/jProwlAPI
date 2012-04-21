package net.sourceforge.prowl.api;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * <p>
 * 
 * This enum lists all available prowl commands with their corresponding
 * api pendant.
 */
public enum ProwlCommand {

	add("add")
	, verify("verify");
	 
	private String apiMapping;
	
	ProwlCommand(String apiMapping) {
		this.apiMapping = apiMapping;
	}
	
	public String getApiMapping() {
		return apiMapping;
	}
	
}
