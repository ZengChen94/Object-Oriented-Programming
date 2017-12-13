package cz39_zx18.client.utils;

import java.awt.Component;

import common.ICRMessageType;
import common.IUserMessageType;

/**
 * NarrowComponent is narrow type datapacket
 */
public class NarrowComponent implements ICRMessageType, IUserMessageType {
	private static final long serialVersionUID = -147210137008349599L;
	private Component component;

	/**
	 * Constructor
	 * @param component The component to be narrowed.
	 */
	public NarrowComponent(Component component) {
		this.component = component;
	}

	/**
	 * Get the component
	 * @return component The component to be narrowed.
	 */
	public Component getComponent() {
		return this.component;
	}
}
