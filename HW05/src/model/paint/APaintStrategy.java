package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import model.Ball;
import model.IPaintStrategy;

/**
 * Second-level abstract class implements an affine transform-based painting process
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public abstract class APaintStrategy implements IPaintStrategy {

	/**
	 * The affine transform used by this paint strategy to translate, scale and rotate the image.
	 */
	private AffineTransform at;

	/**
	 * One parameter constructor
	 * @param at the existing AffineTransform (Probably provided by other strategy)
	 */
	public APaintStrategy(AffineTransform at) {
		this.at = at;
	}

	/**
	 * Protected accessor for the internal affine transform
	 * @return This instance's affine transform
	 */
	protected AffineTransform getAT() {
		return at;
	}

	/* (non-Javadoc)
	 * @see model.IPaintStrategy#paint(java.awt.Graphics, model.CBall)
	 */
	public void paint(Graphics g, Ball host) {
		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);
		at.rotate(host.getVel().x, host.getVel().y);
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);
	}

	/* (non-Javadoc)
	 * @see model.IPaintStrategy#init(model.CBall)
	 */
	public void init(Ball context) {

	}

	/**
	 * This method allows the subclass to inject additional processing 
	 * into the paint method process before the final transformations are performed. 
	 * Only called by the superclass.
	 * @param g The Graphics context that will be drawn upon.
	 * @param host The Ball to be painted.
	 */
	protected void paintCfg(Graphics g, Ball host) {

	}

	/**
	 * This method is designed to be called either by its own APaintStrategy superclass 
	 * or by another paint strategy.
	 * @param g the Graphics
	 * @param host the Ball
	 * @param at the AffineTransform instance
	 */
	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform at);

}
