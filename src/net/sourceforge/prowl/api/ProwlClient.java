package net.sourceforge.prowl.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;

import net.sourceforge.prowl.exception.ProwlException;
import net.sourceforge.prowl.url.ProwlResponseParser;
import net.sourceforge.prowl.url.URLEncoder;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * <p>
 * This prowl client pushes an Prowl event to the prowl service and therefore to your iPhone.
 * It should be pretty easy to use.
 * <p>
 * Example usage:
 * <code><pre>
		ProwlClient c = new ProwlClient();
		ProwlEvent e = new DefaultProwlEvent(
				"myKey", "application", "event",
				"message", 0);
		try {
			String message = c.pushEvent(e);
			System.out.println(message);
		} catch (ProwlException e1) {
			e1.printStackTrace();
		}
 </pre></code>
 *<p>
 * To use this API you need the Prowl app as well as an Prowl account: {@link http://prowl.weks.net/}
 *
 */
public class ProwlClient {

	private String providerKey;
	private String prowlURL = "https://prowl.weks.net/publicapi/";
	private ProwlResponseParser responseParser = new ProwlResponseParser();
	private Proxy proxy = null;
	
	public ProwlClient() {
	}
	
	/**
	 * Pushes an prowl event to the prowl service.
	 * 
	 * @param prowlEvent the prowl event that should be pushed
	 * @return a result message
	 * @throws ProwlException if something went wrong with the request, further details can be found in the exception 
	 * @see ProwlEvent
	 */
	public String pushEvent(ProwlEvent prowlEvent) throws ProwlException {
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
		if(providerKey != null && !providerKey.isEmpty()) {
			prowlURL +="&providerkey="+providerKey;
		}
		
		return prowlURL;
	}
	
	private String sendPushNotification(String url) throws ProwlException {
		try {
			URL requestURL = new URL(url);
			HttpURLConnection connection = null;
			if(proxy == null) {
				connection = (HttpURLConnection) requestURL.openConnection();
			}
			else {
				connection = (HttpURLConnection) requestURL.openConnection(proxy);
			}

			String responseMessage = responseParser.getResponseMessage(connection);
			return responseMessage;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			//TODO: could not connect
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * Sets a http or socks proxy for the connection.
	 * 
	 * @param type the type of the proxy, must be SOCKS or HTTP
	 * @param host the proxy host
	 * @param port the proxy port
	 */
	public void setProxy(String type, String host, int port) {
		SocketAddress sa = new InetSocketAddress(host, port);
		if("SOCKS".equalsIgnoreCase(type)) {
			proxy = new Proxy(Proxy.Type.SOCKS,sa);
		}
		else if("HTTP".equalsIgnoreCase(type)) {
			proxy = new Proxy(Proxy.Type.HTTP,sa);
		}
		else {
			throw new IllegalArgumentException("Unknown proxy type");
		}
	}
	
	/**
	 * Removes the proxy and enables a direct connection.
	 */
	public void removeProxy() {
		proxy = null;
	}
	
	/**
	 * Sets your provider key. This is optional and only necessary if you are
	 * whitelisted for the prowl service. 
	 * 
	 * @param providerKey the provider key
	 */
	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}
	
	/**
	 * Retrieves the provider key.
	 * 
	 * @return the provider key
	 */
	public String getProviderKey() {
		return providerKey;
	}
}
