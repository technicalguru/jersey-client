/**
 * 
 */
package rs.jerseyclient;

/**
 * Holds configuration info for the client.
 * <p>The class can be subclassed to add more config information.</p>
 * 
 * @author ralph
 *
 */
public class JerseyClientConfig {

	private String uri;
	private boolean verbose     = false;
	
	/**
	 * Default constructor.
	 */
	public JerseyClientConfig() {
	}

	/**
	 * Constructor.
	 * @param uri - Base URI of the API that needs to be targeted.
	 * @param verbose - whether the Jersey client shall be configured to be verbose.
	 */
	public JerseyClientConfig(String uri, boolean verbose) {
		this.uri     = uri;
		this.verbose = verbose;
	}

	/**
	 * Returns the base URI of the API that needs to be targeted.
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the base URI of the API that needs to be targeted.
	 * @param uri - Base URI of the API that needs to be targeted.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Returns whether the Jersey client shall be configured to be verbose
	 * @return the verbose
	 */
	public boolean isVerbose() {
		return verbose;
	}

	/**
	 * Sets whether the Jersey client shall be configured to be verbose.
	 * @param verbose - whether the Jersey client shall be configured to be verbose.
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	
}
