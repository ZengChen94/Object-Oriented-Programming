package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

/**
 * 
 * The StraightBall is a subclass of ABall.
 * 
 * @author Chen Zeng (cz39)
 * @author Rong Hu (rh41)
 * @version 20170908
 * @since 20170908
 *
 */

public class StraightBall extends ABall {

	/**
	 * Constructor.
	 * @param location The location of the ball.
	 * @param radius The radius of the ball.
	 * @param velocity The velocity of the ball.
	 * @param color The color of the ball.
	 * @param canvas The canvas of GUI.
	 */
	public StraightBall(Point location, int radius, Point velocity, Color color, Component canvas) {
		super(location, radius, velocity, color, canvas);
	}

	@Override
	public void specifyBall() {
	}
}
