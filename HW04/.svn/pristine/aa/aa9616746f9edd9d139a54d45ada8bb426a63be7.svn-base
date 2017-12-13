package model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Ellipse shape factory
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 */
public class EllipseShapeFactory implements IShapeFactory {

	/**
	 * 
	 */
	public static final EllipseShapeFactory Singleton = new EllipseShapeFactory();

	private EllipseShapeFactory() {

	}

	/* 
	 * Constructor that does nothing
	 */
	@SuppressWarnings("javadoc")
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		Ellipse2D ellipse = new Ellipse2D.Double(x - xScale, y - yScale, xScale, yScale);
		return ellipse;
	}

}
