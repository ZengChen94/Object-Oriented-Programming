package model.paint.strategy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import model.CBall;
import model.paint.FixedColorDecoratorPaintStrategy;
import model.paint.MultiPaintStrategy;

/**
 * Subclass of MultiPaintStrategy that uses a SwimFishPaintStrategy and an 
 * EllipsePaintStrategy to paint a swimming fish shape that has an eye.
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 */
public class NiceFishPaintStrategy extends MultiPaintStrategy {

	/**
	 * No-parameter constructor that instantiates the AffineTransform for internal use.
	 */
	public NiceFishPaintStrategy() {
		this(new AffineTransform());
	}

	/**
	 * Constructor that uses an externally supplied AFfineTransform for internal use.
	 * @param at The AffineTransform to use.
	 */
	public NiceFishPaintStrategy(AffineTransform at) {
		super(at, new Fish1PaintStrategy(), new FixedColorDecoratorPaintStrategy(Color.BLACK,
				new EllipsePaintStrategy(new AffineTransform(), -0.2, 0, 0.2, 0.2)));
	}

	protected void paintCfg(Graphics g, CBall host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVel().y, host.getVel().x)) > Math.PI / 2.0) {
			this.getAT().scale(1.0, -1.0);
		}
	}
}
