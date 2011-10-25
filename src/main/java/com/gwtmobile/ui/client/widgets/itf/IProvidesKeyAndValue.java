package com.gwtmobile.ui.client.widgets.itf;

/**
 * Definiert Objekttraskformatioenn in einer Drop-Down liste.
 * 
 * @author wiese.daniel <br>
 *         copyright (C) 2011, SWM Services GmbH
 * 
 * @param <T> der typ
 */
public interface IProvidesKeyAndValue<T> {

	/**
	 * Get the key for a list item.
	 * 
	 * @param item
	 *            the list item
	 * @return the key that represents the item
	 */
	String getKey(T item);



	/**
	 * Gets the value for the specified Key
	 * 
	 * @param key
	 *            the key
	 * @return the value
	 */
	T getValue(String key);

}
