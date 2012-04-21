package net.sourceforge.prowl.api;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * <p>
 * This interface specifies the methods that are needed to communicate with the Prowl application
 *
 */
public interface ProwlEvent {

	/**
	 * Sets the Prowl api key for your device.
	 * 
	 * @param apiKey your device api key
	 */
	public void setApiKey(String apiKey);
	
	/**
	 * Retrieves your Prowl device api key.
	 * 
	 * @return your device api key
	 */
	public String getApiKey();
	
	/**
	 * Sets the priority of the Prowl event.
	 * The following range is supported:
	 *  
	 * -2: Very Low
	 * -1: Moderate
	 * 0: Normal
	 * 1: High
	 * 2: Emergency.
	 * 
	 * @param prio the event priority
	 */
	public void setPriority(int prio);
	
	/**
	 * Retrieves the priority of the prowl event.
	 * 
	 * @return the event priority
	 */
	public int getPriority();
	
	/**
	 * Sets the name of the application part of the prowl event
	 * 
	 * @param application the application from which comes the event
	 */
	public void setApplication(String application);
	
	/**
	 * Retrieves the application which is the source for this event.
	 * 
	 * @return the source application
	 */
	public String getApplication();
	
	/**
	 * Sets the event name of the prowl event. This should be the action provided by this event. E.g. TwitterNotification
	 * 
	 * @param event the event name
	 */
	public void setEvent(String event);
	
	/**
	 * Retrieves the event name of the prowl event.
	 * 
	 * @return the event name
	 */
	public String getEvent();
	
	/**
	 * Sets the description of the prowl event. This should be the detailed notification message. 
	 * 
	 * @param description the detailed message
	 */
	public void setDescription(String description);
	
	/**
	 * Retrieves the description of the prowl event.
	 * 
	 * @return the description of the prowl event
	 */
	public String getDescription();
	
	/**
	 * <i>Requires Prowl 1.2</i><br/>
	 * Sets the url of the prowl event. This will trigger a redirect when launched, and is viewable in the notification list.
	 * 
	 * @param url the redirect url
	 */
	public void setUrl(String url);
	
	/**
	 * Retrieves the url from the prowl event.
	 * 
	 * @return the url of the prowl event
	 */
	public String getUrl();
}
