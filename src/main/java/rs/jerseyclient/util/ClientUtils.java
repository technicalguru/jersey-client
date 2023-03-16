/**
 * 
 */
package rs.jerseyclient.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

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
	
	/**
	 * Creates a {@link GenericType} reference for a list of given class.
	 * <p>This method is intended to be used with JSON (de)serialization.</p>
	 * @param <T> the type of the items
	 * @param clazz the class of the items
	 * @return the {@link GenericType} for a list of these items
	 */
	public static <T> GenericType<ArrayList<T>> getListTypeRef(Class<T> clazz) {
		return new GenericType<ArrayList<T>>() {};
	}
	
	/**
	 * Creates a {@link GenericType} reference for a set of given class.
	 * <p>This method is intended to be used with JSON (de)serialization.</p>
	 * @param <T> the type of the items
	 * @param clazz the class of the items
	 * @return the {@link GenericType} for a set of these items
	 */
	public static <T> GenericType<Set<T>> getSetTypeRef(Class<T> clazz) {
		return new GenericType<Set<T>>() {};
	}
	
	/**
	 * Creates a {@link GenericType} reference for a map of given key and value classes.
	 * <p>This method is intended to be used with JSON (de)serialization.</p>
	 * @param <K> type of keys
	 * @param <V> type of values
	 * @param keyClass class of keys
	 * @param valueClass class of values
	 * @return the {@link GenericType} for a map of these types
	 */
	public static <K,V> GenericType<Map<K,V>> getMapTypeRef(Class<K> keyClass, Class<V> valueClass) {
		return new GenericType<Map<K,V>>() {};
	}
}
