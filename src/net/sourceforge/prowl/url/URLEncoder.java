package net.sourceforge.prowl.url;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.net.URLCodec;

/**
 * BSD-style license; for more info see http://prowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 *
 */
public class URLEncoder {

	public static String escapeString(String unescaped) {
		if(unescaped != null) {
			URLCodec codec = new URLCodec();
			String encodedString = null;
			try {
				encodedString = codec.encode(unescaped, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encodedString;	
		}
		else {
			return "";
		}
	}
}
