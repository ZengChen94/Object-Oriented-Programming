package cz39_zx18.client.utils;

import common.ICRMessageType;
import common.IUserMessageType;

/**
 * NarrowString is narrow type datapacket
 */
public class NarrowString implements IUserMessageType, ICRMessageType {
	private static final long serialVersionUID = -7078630287513482645L;
	private String data;

	/**
	 * Constructor
	 * @param data The string to be narrowed.
	 */
	public NarrowString(String data) {
		this.data = data;
	}

	/**
	 * Get the string
	 * @return string The component to be narrowed.
	 */
	public String getData() {
		return this.data;
	}
}
