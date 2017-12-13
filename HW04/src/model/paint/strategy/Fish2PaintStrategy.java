package model.paint.strategy;

import java.awt.geom.AffineTransform;

import model.paint.ShapePaintStrategy;
import model.paint.shape.Fish2PolygonFactory;

/**
 * Fish2 Paint Strategy
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class Fish2PaintStrategy extends ShapePaintStrategy {
	/**
	 * constructor for internal use
	 */
	public Fish2PaintStrategy() {
		this(new AffineTransform(), -1.0, -1.0, 2.0 / 5.0, 3.0 / 5.0);
	}

	/**
	 * Call the constructor in superclass
	 * @param at An AffineTransform instance
	 * @param x x-coordinate of the center
	 * @param y y-coordinate of the center
	 * @param width width of the shape
	 * @param height height of the shape
	 */
	public Fish2PaintStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, (new Fish2PolygonFactory()).makeShape(x, y, width, height));
	}
}