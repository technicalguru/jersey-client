package rs.jerseyclient.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * Injects the proxy authorization header into requests.
 * 
 * @author ralph
 *
 */
public class ProxyAuthFilter implements ClientRequestFilter {

	private String authorization;
	
	/**
	 * Constructor.
	 * @param username proxy authorization user
	 * @param password proxy authorization password
	 */
	public ProxyAuthFilter(String username, String password) {
		String s = username+":"+password;
		authorization = "Basic "+Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("Proxy-Authorization", authorization);
	}

	
}
