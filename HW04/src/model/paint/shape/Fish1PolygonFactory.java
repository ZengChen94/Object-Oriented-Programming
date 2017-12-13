package model.paint.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Fish1 Polygon Factory
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class Fish1PolygonFactory extends PolygonFactory {
	/**
	 * The percentage of the average of the width and 
	 * height of the image that defines a unit radius for the image.
	 */
	private static final double SCALE_FACTOR = 0.33;

	/**
	 * Points to draw the fish
	 */
	private static final Point[] PTS = { new Point(0, 4), new Point(0, 8), new Point(2, 6), new Point(2, 12),
			new Point(8, 6), new Point(2, 0), new Point(2, 6) };

	/**
	 * The constructor of Fish1
	 */
	public Fish1PolygonFactory() {
		super(new AffineTransform(), SCALE_FACTOR, PTS);
	}
}
