package model.paint.shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * The polygon factory
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class PolygonFactory implements IShapeFactory {
	private Polygon poly = new Polygon();

	private AffineTransform at = new AffineTransform();

	private double scaleFactor = 1.0;

	/* (non-Javadoc)
	 * @see model.paint.shape.IShapeFactory#makeShape(double, double, double, double)
	 */
	@SuppressWarnings("javadoc")
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor); // optional rotation can be added as well
		return at.createTransformedShape(poly);
	}

	/**
	 * @param at The AffineTransform to use.
	 * @param scaleFactor The ratio of the desired unit size to the defined size of the prototype Polygon.
	 * @param pts Vararg parameters that are the Points that define the Polygon around the origin as its center.
	 */
	public PolygonFactory(AffineTransform at, double scaleFactor, Point... pts) {
		this.at = at;
		this.scaleFactor = scaleFactor;
		for (int i = 0; i < pts.length; i++) {
			this.poly.addPoint(pts[i].x, pts[i].y);
		}
	}
}
