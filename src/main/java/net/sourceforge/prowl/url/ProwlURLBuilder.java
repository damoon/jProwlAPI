package net.sourceforge.prowl.url;

import net.sourceforge.prowl.api.ProwlCommand;
import net.sourceforge.prowl.api.ProwlParameter;

/**
 * BSD-style license; for more info see http://jprowlapi.sourceforge.net/
 */

/**
 * @author Christian Ternes
 * 
 * This fluent interface can be used to construct a valid prowl api url.
 */
public interface ProwlURLBuilder {

	/**
	 * Uses a specific api command like <i>add</i>.
	 * This should be only called once per {@link ProwlURLBuilder} instance.
	 * 
	 * @param command the api command, one of {@link ProwlCommand}
	 * @return a {@link ProwlURLBuilder} with set command 
	 */
	public ProwlURLBuilder useCommand(ProwlCommand command);
	
	/**
	 * Adds a prowl parameter like <i>apikey</i>.
	 * This can be called several times per {@link ProwlURLBuilder} instance.
	 * 
	 * @param parameter a prowl api parameter, one of {@link ProwlParameter}
	 * @param value the value of the parameter
	 * @return a {@link ProwlURLBuilder} with set parameter value 
	 */
	public ProwlURLBuilder appendParam(ProwlParameter parameter, String value);
	
	/**
	 * Retrieves the constructed prowl api url.
	 * This should be called at the end when command and parameters are 
	 * already set.
	 * 
	 * @return the finally constructed prowl api url
	 */
	public String getURL();
	
}
