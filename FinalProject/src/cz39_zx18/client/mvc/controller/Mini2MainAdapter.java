package cz39_zx18.client.mvc.controller;

import java.awt.Component;

/**
 * Adapter for mini-mvc talking with main-mvc
 */
public interface Mini2MainAdapter {

	/**
	 * Remove a mini view from the main view
	 * @param miniView the mini view to remove
	 */
	public void remove(Component miniView);

}
