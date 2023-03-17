package rs.jerseyclient.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.glassfish.jersey.client.HttpUrlConnectorProvider.ConnectionFactory;;

/**
 * Proxy Connection Factory.
 * 
 * @author ralph
 *
 */
public class ProxyConnectionFactory implements ConnectionFactory {

	private ProxyConfig config;
	
	/**
	 * Constructor.
	 * @param config the proxy configuration
	 */
	public ProxyConnectionFactory(ProxyConfig config) {
		this.config = config;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HttpURLConnection getConnection(URL url) throws IOException {
		return (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxyHost(), config.getProxyPort())));
	}

	
}
