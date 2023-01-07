/**
 * 
 */
package rs.jerseyclient;

import javax.ws.rs.client.Client;

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
	 * @param config - the config to be used
	 */
	public JerseyClient(JerseyClientConfig config) {
		this(config, null);
	}
	
	/**
	 * Constructor.
	 * @param config - the config to be used
	 * @param clientFilter - a specific client filter to be used (defaults to {@link ClientFilter} if {@code null})
	 */
	public JerseyClient(JerseyClientConfig config, ClientFilter clientFilter) {
		super();
		configure(config, filter);
		authorize();
	}

	/**
	 * Configures JAX-RS client and main web target.
	 * @param config - the config to be used
	 * @param clientFilter - a specific client filter to be used (defaults to {@link ClientFilter} if {@code null})
	 */
	protected void configure(JerseyClientConfig config, ClientFilter clientFilter) {
		this.config = config;
		this.client = ClientUtils.createClient(config.isVerbose());
		this.filter = clientFilter != null ? clientFilter : new ClientFilter(NAME+"/"+VERSION+" ("+URL+")");
		
		setTarget(client.target(config.getUri()));
		getTarget().register(this.filter);
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
