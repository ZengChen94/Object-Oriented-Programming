package cz39_zx18.client.utils;

import javax.swing.ImageIcon;

import common.ICRMessageType;
import common.IUserMessageType;

/**
 * NarrowImageIcon is narrow type datapacket
 */
public class NarrowImageIcon implements ICRMessageType, IUserMessageType {
	private static final long serialVersionUID = -3902442671430309780L;
	private ImageIcon image;

	/**
	 * Constructor
	 * @param image The image to be narrowed.
	 */
	public NarrowImageIcon(ImageIcon image) {
		this.image = image;
	}

	/**
	 * Get the image
	 * @return image The component to be narrowed.
	 */
	public ImageIcon getImage() {
		return image;
	}
}
