/**
 * 
 */
package rs.jerseyclient.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.NewCookie;

/**
 * Handles cookies in requests and responses.
 * 
 * @author ralph
 *
 */
public class UserAgentFilter implements ClientRequestFilter {

	private String                 userAgent;
	
	/**
	 * Default Constructor.
	 */
	public UserAgentFilter() {
		this(null);
	}

	/**
	 * Constructor.
	 * @param userAgent - the user agent string to be used.
	 */
	public UserAgentFilter(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * Sets user agent if it was set.
	 */
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		if (userAgent != null) requestContext.getHeaders().add("User-Agent", userAgent);
	}

}
