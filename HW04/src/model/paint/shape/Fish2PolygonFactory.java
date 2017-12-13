package model.paint.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Fish2 Polygon Factory
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class Fish2PolygonFactory extends PolygonFactory {
	/**
	 * The percentage of the average of the width 
	 * and height of the image that defines a unit radius for the image.
	 */
	private static final double SCALE_FACTOR = 0.33;
	/**
	 * points to paint the fish
	 */
	private static final Point[] PTS = { new Point(0, 2), new Point(0, 10), new Point(2, 6), new Point(2, 12),
			new Point(8, 6), new Point(2, 0), new Point(2, 6) };

	/**
	 * default constructor of Fish1PolygonFactory 
	 */
	public Fish2PolygonFactory() {
		super(new AffineTransform(), SCALE_FACTOR, PTS);
	}
}
