/**
 * 
 */
package rs.jerseyclient;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.ClientConfig;

import rs.jerseyclient.util.AbstractClient;
import rs.jerseyclient.util.ClientFilter;
import rs.jerseyclient.util.ClientUtils;


/**
 * The main entrance class.
 * 
 * @author ralph
 *
 */
public class JerseyClient extends AbstractClient {

	/** Default name for User-Agent */
	public static String NAME    = "jersey-client";
	/** Default version for User-Agent */
	public static String VERSION = "1.0.0";
	/** Default URL for User-Agent */
	public static String URL     = "https://github.com/technicalguru/jersey-client";
	
	private Client             client;
	private ClientFilter       filter;
	private JerseyClientConfig config;

	/**
	 * Constructor.
	 * <p>Be aware that the constructor immediately calls {@link #configure(JerseyClientConfig, ClientFilter)} and
	 *    {@link #authorize()}.</p>
	 * @param config - the config to be used
	 */
	public JerseyClient(JerseyClientConfig config) {
		this(config, null);
	}
	
	/**
	 * Constructor.
	 * <p>Be aware that the constructor immediately calls {@link #configure(JerseyClientConfig, ClientFilter)} and
	 *    {@link #authorize()}.</p>
	 * @param config - the config to be used
	 * @param clientFilter - a specific client filter to be used (defaults to {@link ClientFilter} if {@code null})
	 */
	public JerseyClient(JerseyClientConfig config, ClientFilter clientFilter) {
		super();
		configure(config, clientFilter);
		authorize();
	}

	/**
	 * Configures JAX-RS client and main web target.
	 * @param config - the config to be used
	 * @param clientFilter - a specific client filter to be used (defaults to {@link ClientFilter} if {@code null})
	 * @see #createClientConfig()
	 */
	protected void configure(JerseyClientConfig config, ClientFilter clientFilter) {
		this.config = config;
		this.client = createClient();
		this.filter = clientFilter != null ? clientFilter : new ClientFilter(NAME+"/"+VERSION+" ("+URL+")");
		
		setTarget(client.target(config.getUri()));
		getTarget().register(this.filter);
	}
	
	/**
	 * Creates the Jersey client configuration based on the config for this client.
	 * <p>Descendants can override this method in order to manipulate the config.</p>
	 * @return the config to be used for the JAX-WS Jersey client.
	 * @see ClientUtils#createClientConfig(JerseyClientConfig)
	 */
	protected ClientConfig createClientConfig() {
		return ClientUtils.createClientConfig(config);
	}

	/**
	 * Creates the actual JAX-WS Jersey client instance.
	 * <p>Desecendants can override this method when they want full control over the creation of the client.</p>
	 * @return the client
	 * @see #createClientConfig()
	 */
	protected Client createClient() {
		return ClientUtils.createClient(createClientConfig());
	}
	
	/**
	 * Returns the configured Jersey client.
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Returns the filter applied to the Jersey client.
	 * @return the filter
	 */
	public ClientFilter getFilter() {
		return filter;
	}

	/**
	 * Returns the config of this client.
	 * @return the config
	 */
	public JerseyClientConfig getConfig() {
		return config;
	}

	/**
	 * Base method for authentication.
	 * <p>The method is called after configuration from Constructor. You can override it 
	 * to implement an automatic authentication.</P>
	 * <p>The default implementation does nothing.</p>
	 */
	protected void authorize() {
	}
	
	/**
	 * Close the client.
	 */
	public void close() {
		client.close();
	}

}
