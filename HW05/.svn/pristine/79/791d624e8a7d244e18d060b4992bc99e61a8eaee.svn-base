package model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Rectangle Shape Factory
 * 
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 */
public class RectangleShapeFactory implements IShapeFactory {

	/**
	 * 
	 */
	public static final RectangleShapeFactory Singleton = new RectangleShapeFactory();

	private RectangleShapeFactory() {

	}

	/* (non-Javadoc)
	 * @see model.paint.shape.IShapeFactory#makeShape(double, double, double, double)
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		Rectangle2D rectangle = new Rectangle2D.Double(x - xScale, y - yScale, 2 * xScale, 2 * yScale);
		return rectangle;
	}

}
