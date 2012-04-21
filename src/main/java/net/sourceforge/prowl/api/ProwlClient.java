package net.sourceforge.prowl.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;

import net.sourceforge.prowl.exception.ProwlException;
import net.sourceforge.prowl.url.DefaultProwlURLBuilder;
import net.sourceforge.prowl.url.ProwlResponseParser;
import net.sourceforge.prowl.url.ProwlURLBuilder;
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
 * To use this API you need the Prowl app as well as an Prowl account: {@link http://www.prowlapp.com/}
 *
 */
public class ProwlClient {

	private String providerKey;
	private ProwlResponseParser responseParser = new ProwlResponseParser();
	private Proxy proxy = null;
	private int connectionTimeout = 5000; //default = 5s
	private int readTimeout = 5000; //default = 5s
	
	public ProwlClient() {
	}
	
	/**
	 * Constructs a new ProwlClient with the given timeouts.
	 * If the timeouts are < 0 the default timeouts will be used instead.
	 * 
	 * @param connectionTimeout the connection timeout in ms, DEFAULT = 5000
	 * @param readTimeout the read timeout in ms, DEFAULT = 5000
	 */
	public ProwlClient(int connectionTimeout, int readTimeout) {
		if(connectionTimeout > 0) {
			this.connectionTimeout = connectionTimeout;
		}
		if(readTimeout > 0) {
			this.readTimeout = readTimeout;
		}
	}
	
	
	/**
	 * Pushes an prowl event to the prowl service.
	 * 
	 * @param prowlEvent the prowl event that should be pushed
	 * @return a result message
	 * @throws ProwlException if something went wrong with the request, further details can be found in the exception
	 * @throws IllegalArgumentException if required data in the prowl event are not set or priority is out of range 
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
		
		ProwlURLBuilder prowlUrl = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.add);

		String apiKey = prowlEvent.getApiKey();
		apiKey = URLEncoder.escapeString(apiKey);
		prowlUrl.appendParam(ProwlParameter.apikey, apiKey);
		
		String providerKey = this.providerKey;
		if (providerKey != null) {
			providerKey = URLEncoder.escapeString(providerKey);
			prowlUrl.appendParam(ProwlParameter.providerkey, providerKey);
		}
		
		String application = prowlEvent.getApplication();
		application = URLEncoder.escapeString(application);
		prowlUrl.appendParam(ProwlParameter.application, application);
		
		prowlUrl.appendParam(ProwlParameter.priority, String.valueOf(priority));

		String event = prowlEvent.getEvent();
		event = URLEncoder.escapeString(event);
		prowlUrl.appendParam(ProwlParameter.event, event);
		
		String desc = prowlEvent.getDescription();
		desc = URLEncoder.escapeString(desc);
		prowlUrl.appendParam(ProwlParameter.description, desc);
		
		String url = prowlEvent.getUrl();
		if (url != null) {
			url = URLEncoder.escapeString(url);
			prowlUrl.appendParam(ProwlParameter.url, url);
		}
		
		String finalUrl = prowlUrl.getURL();
		String response = sendPushNotification(finalUrl);
		
		return response;
	}

	protected String sendPushNotification(String url) throws ProwlException {
		try {
			URL requestURL = new URL(url);
			HttpURLConnection connection = null;
			if(proxy == null) {
				connection = (HttpURLConnection) requestURL.openConnection();
			}
			else {
				connection = (HttpURLConnection) requestURL.openConnection(proxy);
			}
			//set timeouts
			connection.setConnectTimeout(connectionTimeout);
			connection.setReadTimeout(readTimeout);

			String responseMessage = responseParser.getResponseMessage(connection);
			return responseMessage;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch(SocketTimeoutException ste) {
			ste.printStackTrace();
			return ste.getMessage();
		}
		catch (IOException ioe) {
			//TODO: could not connect
			ioe.printStackTrace();
			return ioe.getMessage();
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
	
	/**
	 * Retrieves the prowl server url.
	 * 
	 * @return the prowl server url
	 */
	public String getProwlUrl() {
		return DefaultProwlURLBuilder.getProwlApiUrl();
	}
	
	/**
	 * Sets the prowl server url. This is optional and only necessary if the prowl server url changes. 
	 * 
	 * @param prowlUrl the prowl server url
	 */
	public void setProwlUrl(String url) {
		DefaultProwlURLBuilder.setProwlApiUrl(url);
	}
	
	/**
	 * Verifies the given api key. 
	 * Returns true if the api key is valid, false if api key is invalid.
	 * 
	 * @param apiKey the api key to verify
	 * @return true if the api key is valid, false otherwise
	 * @throws ProwlException if something went wrong with the request, further details can be found in the exception
	 */
	public boolean verifyApiKey(String apiKey) throws ProwlException {
		try {
			String prowlUrl = DefaultProwlURLBuilder.createUrl().useCommand(ProwlCommand.verify).appendParam(ProwlParameter.apikey, apiKey).getURL();
			sendPushNotification(prowlUrl);
		}
		catch(ProwlException e) {
			if(401 == e.getResponseCode()) {
				return false;
			}
			else {
				throw e;
			}
		}
		return true;
	}
}
