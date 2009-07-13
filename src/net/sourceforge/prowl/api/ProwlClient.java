package net.sourceforge.prowl.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.sourceforge.prowl.proxy.ProxyWrapper;
import net.sourceforge.prowl.url.ProwlResponseParser;
import net.sourceforge.prowl.url.URLEncoder;

/**
 * BSD-style license; for more info see http://prowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * 
 * This prowl client pushes an Prowl event to the prowl service and therefore to your iPhone.
 * It should be pretty easy to use.
 * For example:
 * <code>
 		ProwlEvent event = new DefaultProwlEvent("12345", 
				"application", "event", "This is just a little test", 0);
		
		ProwlClient pc = new ProwlClient();
		String response = pc.pushEvent(event);
 *  </code>
 *
 * To use this API you need an the Prowl app as well as an Prowl account: http://prowl.weks.net/
 *
 */
public class ProwlClient {

	private String providerKey;
	private String prowlURL = "https://prowl.weks.net/publicapi/";
	private ProwlResponseParser responseParser = new ProwlResponseParser();
	
	public ProwlClient() {
	}
	
	/**
	 * Pushes an prowl event to the prowl service.
	 * 
	 * @param prowlEvent the prowl event that should be pushed
	 * @return a result message
	 * @see ProwlEvent
	 */
	public String pushEvent(ProwlEvent prowlEvent) {
		if(prowlEvent == null || prowlEvent.getApiKey()==null || prowlEvent.getApplication()==null ||
				prowlEvent.getDescription()==null || prowlEvent.getEvent()==null) {
			throw new IllegalArgumentException("Arguments must not be null");
		}
		
		//check priority in range?
		int priority = Integer.valueOf(prowlEvent.getPriority());
		if(priority < -2 || priority > 2) {
			throw new IllegalArgumentException("The priority is out of range -2<x>2");
		}
		
		String apiKey = prowlEvent.getApiKey();
		String application = prowlEvent.getApplication();
		String event = prowlEvent.getEvent();
		String desc = prowlEvent.getDescription();

		//escape all strings
		application = URLEncoder.escapeString(application);
		event = URLEncoder.escapeString(event);
		desc = URLEncoder.escapeString(desc);
		
		String prowlURL = constructURL(apiKey, application, event, desc, priority);
		String response = sendPushNotification(prowlURL);
		
		return response;
	}

	private String constructURL(String apiKey, String application, String event,
			String desc, int priority) {
		apiKey = "apikey="+apiKey;
		application = "application="+application;
		event = "event="+event;
		desc = "description="+desc;
		String prio = "priority="+priority;
		
		prowlURL += "add?"+apiKey+"&"+application+"&"+event+"&"+desc+"&"+prio;
		return prowlURL;
	}
	
	private String sendPushNotification(String url) {
		try {
			URL requestURL = new URL(url);
			InputStream openStream = requestURL.openStream();
			return responseParser.getResponseMessage(openStream);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public void setProxy(String type, String host, String port) {
		ProxyWrapper.enableGlobalProxy(type, host, port);
	}
	
	public void removeProxy() {
		ProxyWrapper.disableGlobalProxy();
	}
	
	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}
	
	public String getproviderKey() {
		return providerKey;
	}
}
