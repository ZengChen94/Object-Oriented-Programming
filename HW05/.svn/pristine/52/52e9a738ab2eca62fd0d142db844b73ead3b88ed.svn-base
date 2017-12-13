package model.paint;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.CBall;

/**
 * A shape painting strategy that adds a paintCfg override that keeps the shape upright no matter which direction it is going.
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 */
public class UprightShapePaintStrategy extends ShapePaintStrategy {
	/**
	 * @param shape extends the shape
	 */
	public UprightShapePaintStrategy(Shape shape) {
		super(shape);
	}

	/**
	 * Constructor that takes both a Shape and an affine transform
	 * @param at The affine transform object to use
	 * @param shape The prototype shape to use.
	 */
	public UprightShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at, shape);
	}

	@Override
	protected void paintCfg(java.awt.Graphics g, CBall host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVel().y, host.getVel().x)) > Math.PI / 2.0) {
			getAT().scale(1.0, -1.0);
		}
	}
}
