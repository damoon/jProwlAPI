package net.sourceforge.prowl.proxy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * BSD-style license; for more info see http://prowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 *
 */
public class ProxyWrapper {

	public static void enableGlobalProxy(String type, String host, String port) {
		Properties properties = System.getProperties();
		properties.put("proxySet", "true"); 

		if(type.equalsIgnoreCase("SOCKS")) {
			properties.put("socksProxyHost", host);
			properties.put("socksProxyPort", Integer.parseInt(port));	
		}
		else {
			properties.put("proxyHost", host);
			properties.put("proxyPort", Integer.parseInt(port));
		}
	}
	
	public static void disableGlobalProxy() {
		Properties properties = System.getProperties();
		properties.put("proxySet", "false"); 
	}
	
	public static URLConnection getURLConnection(String url, String type, String host, String port) {
		SocketAddress sa = new InetSocketAddress(host, Integer.parseInt(port));
		
		Proxy proxy = null;
		if(type.equalsIgnoreCase("SOCKS")) {
			proxy = new Proxy(Proxy.Type.SOCKS,sa);	
		}
		else if(type.equalsIgnoreCase("HTTP")) {
			proxy = new Proxy(Proxy.Type.HTTP,sa);
		}
		
		try {
			URL link = new URL(url);
			URLConnection connection = null;
			if(proxy != null) {
				connection  = link.openConnection(proxy);	
			}
			else {
				connection = link.openConnection();
			}
			
			return connection;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
