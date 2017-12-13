package model.paint;

import java.awt.geom.AffineTransform;
import model.CBall;
import java.awt.Graphics;

/**
 * Abstract class extends APaintStrategy that provides default behavior for 
 * subclasses that will decorate another IPaintStrategy to add functionality to that strategy. 
 * All this class's methods do is to simply delegate to the decoree. 
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 */
public abstract class ADecoratorPaintStrategy extends APaintStrategy {

	/**
	 * The "decoree" paint strategy whose methods are being 
	 * augmented by this decorator paint strategy.
	 */
	private APaintStrategy decoree;

	/**
	 * Constructor that takes the decoree paint strategy
	 * @param decoree The paint strategy that is to be decorated
	 */
	public ADecoratorPaintStrategy(APaintStrategy decoree) {
		super(decoree.getAT());
		this.decoree = decoree;
	}

	/**
	 * Default behavior is to simply delegate to the decoree's paint method
	 * @param g graphics to paint
	 * @param host ball object
	 */
	public void paint(Graphics g, CBall host) {
		decoree.paint(g, host);
	}

	/**
	 * Default behavior is to simply delegate to the decoree's paintXfrm method
	 * @param g Graphics
	 * @param host Ball object
	 * @param at Affinetransform 
	 */
	public void paintXfrm(Graphics g, CBall host, AffineTransform at) {
		decoree.paintXfrm(g, host, at);
	}

	/**
	 * Default behavior is to simply delegate to the decoree's init method
	 * @param host Ball object
	 */
	public void init(CBall host) {
		decoree.init(host);
	}
}
