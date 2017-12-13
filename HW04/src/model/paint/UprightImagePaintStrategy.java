package model.paint;

import model.CBall;

/**
 * An image painting strategy that adds a paintCfg 
 * override that keeps the image upright no matter which way it is going.
 * 
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class UprightImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * Constructor for an image painting strategy
	 * @param filename Fully qualified filename of the image file RELATIVE TO THIS PACKAGE, using a forward slash as a directory divider.
	 * @param fillFactor The ratio of the effective diameter (hit circle) of the image to the average of its width and height.
	 */
	public UprightImagePaintStrategy(String filename, double fillFactor) {
		super(filename, fillFactor);
	}

	@Override
	protected void paintCfg(java.awt.Graphics g, CBall host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVel().y, host.getVel().x)) > Math.PI / 2.0) {
			getAT().scale(1.0, -1.0);
		}
	}

}
