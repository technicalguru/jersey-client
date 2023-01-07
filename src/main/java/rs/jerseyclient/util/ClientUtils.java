/**
 * 
 */
package rs.jerseyclient.util;

import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

/**
 * Some useful methods.
 * 
 * @author ralph
 *
 */
public class ClientUtils {

	/**
	 * Create a JAX-WS client.
	 * @param verbose - whether verbosity shall be enabled.
	 * @return the client created
	 */
	public static Client createClient(boolean verbose) {
		ClientConfig clientConfig = new ClientConfig();
		if (verbose) {
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_TEXT);
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName());
		}
		return ClientBuilder.newClient(clientConfig);
	}
	
}
