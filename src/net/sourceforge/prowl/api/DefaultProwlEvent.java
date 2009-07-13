package net.sourceforge.prowl.api;

/**
 * BSD-style license; for more info see http://prowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 */
public class DefaultProwlEvent implements ProwlEvent {

	private String apiKey;
	private String description;
	private String event;
	private String application;
	private int priority = 0;
	
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
		this.apiKey = apiKey;
		this.application = application;
		this.event = event;
		this.description = description;
		this.priority = priority;
	}
	
	@Override
	public String getApiKey() {
		return apiKey;
	}
	@Override
	public String getApplication() {
		return application;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public String getEvent() {
		return event;
	}
	@Override
	public int getPriority() {
		return priority;
	}
	@Override
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@Override
	public void setApplication(String application) {
		this.application = application;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public void setEvent(String event) {
		this.event = event;
	}
	@Override
	public void setPriority(int prio) {
		this.priority = prio;
	}
	
}
