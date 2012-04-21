package net.sourceforge.prowl.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sourceforge.prowl.exception.ProwlException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * <p>
 * ProwlResponseParser is a class to extract a response from the prowl service.
 *
 */
public class ProwlResponseParser {
	
	/**
	 * Retrieves the response message from the prowl service
	 * 
	 * @param connection the current connection to the prowl service
	 * @return the response message
	 * @throws ProwlException if something went wrong with the request, further details can be found in the exception
	 */
	public String getResponseMessage(HttpURLConnection connection) throws ProwlException {
		if(connection == null) {
			throw new IllegalArgumentException("connection must not be null");
		}
		checkForErrors(connection);
		
		try {
			InputStream inputStream = connection.getInputStream();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		    DocumentBuilder builder = factory.newDocumentBuilder(); 
		    Document document = builder.parse(inputStream); 
		    NodeList childNodes = document.getChildNodes();
		    if(childNodes.getLength() > 0) {
		    	Node prowl = childNodes.item(0);
		    	Node result = prowl.getFirstChild().getNextSibling();
		    	if(result != null) {
		    		NamedNodeMap attributes = result.getAttributes();
		    		if("success".equalsIgnoreCase(result.getNodeName())) {
			    		Node remaining = attributes.getNamedItem("remaining");
			    		return "API call succeeded. "+remaining.getTextContent()+ " api calls left.";
		    		}
		    		else {
		    			Node errorCode = attributes.getNamedItem("code");
		    			return "API call failed with code="+errorCode.getTextContent()+". "+result.getTextContent();
		    		}
 		    		
		    	}
		    }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void checkForErrors(HttpURLConnection connection) throws ProwlException {
		try {
			int code = connection.getResponseCode();
			if(code == 400) {
				throw new ProwlException(code, "Bad request, the parameters you provided did not validate.");
			}
			else if(code == 401) {
				throw new ProwlException(code, "The API key given is not valid, and does not correspond to a user.");
			}
			else if(code == 405) {
				throw new ProwlException(code, "Method not allowed, you attempted to use a non-SSL connection to Prowl.");
			}
			else if(code == 406) {
				throw new ProwlException(code, "Your IP address has exceeded the API limit.");
			}
			else if(code == 500) {
				throw new ProwlException(code, "Internal server error, something failed to execute properly on the Prowl side.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
