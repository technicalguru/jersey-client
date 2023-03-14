/**
 * 
 */
package rs.jerseyclient.util;

import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import com.fasterxml.jackson.core.util.JacksonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Some useful methods.
 * 
 * @author ralph
 *
 */
public class ClientUtils {

	/**
	 * Create a JAX-WS client.
	 * 
	 * @param verbose - whether verbosity shall be enabled.
	 * @return the client created
	 */
	public static Client createClient(boolean verbose) {
		return createClient(verbose, null);
	}
	
	/**
	 * Create a JAX-WS client with a custom {@link ObjectMapper}.
	 * 
	 * @param verbose - whether verbosity shall be enabled.
	 * @param objectMapper - a custom ObjectMapper for JSON
	 * @return the client created
	 */
	public static Client createClient(boolean verbose, ObjectMapper objectMapper) {
		ClientConfig clientConfig = new ClientConfig();
		if (verbose) {
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_TEXT);
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName());
		}
		if (objectMapper != null) {
			ObjectMapperProvider.setMapper(objectMapper);
			clientConfig.register(ObjectMapperProvider.class);
			clientConfig.register(JacksonFeature.class);
		}
		return ClientBuilder.newClient(clientConfig);
	}
	
}
