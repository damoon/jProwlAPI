package net.sourceforge.prowl.api;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 */
public class DefaultProwlEvent implements ProwlEvent {

	private String apiKey;
	private String description;
	private String event;
	private String application;
	private String url; 
	private int priority = 0;
	
	/**
	 * Constructs a new empty prowl event
	 */
	public DefaultProwlEvent() {
	}
	
	/**
	 * Constructs a new prowl event with all necessary parameters.
	 * 
	 * @param apiKey your device api key
	 * @param application the application from which comes the event
	 * @param event the event name of the prowl event
	 * @param description the detailed message of the prowl event
	 * @param priority the event priority between -2 and 2
	 */
	public DefaultProwlEvent(String apiKey, String application, String event, 
			String description, int priority) {
		this(apiKey, application, event, description, priority, null);
	}
	
	/**
	 * Constructs a new prowl event with all necessary parameters.
	 * 
	 * @param apiKey your device api key
	 * @param application the application from which comes the event
	 * @param event the event name of the prowl event
	 * @param description the detailed message of the prowl event
	 * @param priority the event priority between -2 and 2
	 * @param url the url of the prowl event
	 */
	public DefaultProwlEvent(String apiKey, String application, String event, 
			String description, int priority, String url) {
		this.apiKey = apiKey;
		this.application = application;
		this.event = event;
		this.description = description;
		this.priority = priority;
		this.url = url;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public String getApplication() {
		return application;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getEvent() {
		return event;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void setApplication(String application) {
		this.application = application;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public void setPriority(int prio) {
		this.priority = prio;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
}
