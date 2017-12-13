package model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.CBall;

/**
 * shapepaintstrategy for painting a shape
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class ShapePaintStrategy extends APaintStrategy {
	private Shape shape;

	/**
	 * @param at affinetransform object
	 * @param makeShape the shape to make
	 */
	public ShapePaintStrategy(AffineTransform at, Shape makeShape) {
		super(at);
		this.shape = makeShape;
	}

	/**
	 * @param shape instance
	 */
	public ShapePaintStrategy(Shape shape) {
		//		AffineTransform at = new AffineTransform();
		this(new AffineTransform(), shape);
	}

	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#paintXfrm(java.awt.Graphics, model.CBall, java.awt.geom.AffineTransform)
	 */
	@SuppressWarnings("javadoc")
	@Override
	public void paintXfrm(Graphics g, CBall host, AffineTransform at) {
		// TODO Auto-generated method stub
		((Graphics2D) g).fill(at.createTransformedShape(shape));
	}

}
