package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import util.Randomizer;

/**
 * 
 * The CurveBall is a subclass of ABall.
 * 
 * @author Chen Zeng (cz39)
 * @author Rong Hu (rh41)
 * @version 20170908
 * @since 20170908
 *
 */

public class CurveBall extends ABall {
	double theta = Randomizer.Singleton.randomDouble(-Math.PI / 20, Math.PI / 20);

	/**
	 * Constructor.
	 * @param location The location of the ball.
	 * @param radius The radius of the ball.
	 * @param velocity The velocity of the ball.
	 * @param color The color of the ball.
	 * @param canvas The canvas of GUI.
	 */
	public CurveBall(Point location, int radius, Point velocity, Color color, Component canvas) {
		super(location, radius, velocity, color, canvas);
	}

	/**
	 * Change the velocity of the ball according to angle theta.
	 */
	@Override
	public void specifyBall() {
		Point velocity_old = get_velocity();
		Point velocity_new = new Point();
		velocity_new.x = (int) Math.round(velocity_old.x * Math.cos(theta) - velocity_old.y * Math.sin(theta));
		velocity_new.y = (int) Math.round(velocity_old.y * Math.cos(theta) + velocity_old.x * Math.sin(theta));
		set_velocity(velocity_new);
	}
}
