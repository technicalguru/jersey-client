/**
 * 
 */
package rs.jerseyclient.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.GenericType;

/**
 * Some useful methods.
 * 
 * @author ralph
 *
 */
public class ClientUtils {

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
