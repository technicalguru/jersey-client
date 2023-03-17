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
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.logging.LoggingFeature;

import com.fasterxml.jackson.core.util.JacksonFeature;

import rs.jerseyclient.JerseyClientConfig;

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
		JerseyClientConfig config = new JerseyClientConfig();
		config.setVerbose(verbose);
		return createClient(createClientConfig(config));
	}
	
	/**
	 * Create a JAX-WS client config based on the jersey client config.
	 * 
	 * @param config - the configuration to be used
	 * @return the client configuration created
	 * @see #applyProxyConfig(ClientConfig, ProxyConfig)
	 */
	public static ClientConfig createClientConfig(JerseyClientConfig config) {
		ClientConfig clientConfig = new ClientConfig();
		if (config.isVerbose()) {
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_TEXT);
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName());
		}
		if (config.getObjectMapper() != null) {
			ObjectMapperProvider.setMapper(config.getObjectMapper());
			clientConfig.register(ObjectMapperProvider.class);
			clientConfig.register(JacksonFeature.class);
		}
		ProxyConfig proxyConfig = config.getProxyConfig();
		if ((proxyConfig != null) && (proxyConfig.getProxyHost() != null)) {
			applyProxyConfig(clientConfig, proxyConfig);
		}

		return clientConfig;
	}
	
	/**
	 * Configures the proxy based on the given proxy.
	 * @param config - the Jersey config to change
	 * @param proxyConfig - the proxy configuration
	 */
	public static void applyProxyConfig(ClientConfig config, ProxyConfig proxyConfig) {
		config.connectorProvider(new HttpUrlConnectorProvider().connectionFactory(new ProxyConnectionFactory(proxyConfig)));
		if (proxyConfig.getUsername() != null) {
			config.register(new ProxyAuthFilter(proxyConfig.getUsername(), proxyConfig.getPassword()));
		}
	}

	/**
	 * Create a JAX-WS client based on the client config.
	 * @param config the Jersey client config
	 * @return the client created
	 */
	public static Client createClient(ClientConfig config) {
		return ClientBuilder.newClient(config);
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
