package net.sourceforge.prowl.url;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * BSD-style license; for more info see http://prowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 *
 * ProwlResponseParser is a class to parse an input stream from the prowl service and 
 * extract the response message from it.
 *
 */
public class ProwlResponseParser {
	
	/**
	 * Retrieves the response message from the prowl service
	 * 
	 * @param in the inputstream from the prowl service
	 * @return the response message
	 */
	public String getResponseMessage(InputStream in) {
		if(in == null) {
			throw new IllegalArgumentException("InputStream must not be null");
		}
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		    DocumentBuilder builder = factory.newDocumentBuilder(); 
		    Document document = builder.parse(in); 
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
}
