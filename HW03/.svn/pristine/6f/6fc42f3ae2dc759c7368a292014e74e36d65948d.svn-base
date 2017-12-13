package model.strategy;

import model.Ball;
import util.SineMaker;

/**
 * Simple strategy for spinning a ball in a "sin" manner (in a vortex)
 * 
 * @author cz39
 * @author ker7
 */
public class SpinStrategy implements IUpdateStrategy {
	/**
	 * spin range
	 **/
	private SineMaker sin_val = new SineMaker(-25, 25, 25);

	/**
	 * Variant part of the Spinning ball uses sinusoidal waves to cause the ball to spin
	 */
	@Override
	public void updateState(Ball ball) {
		ball.getVelocity().x = sin_val.getIntVal();
		ball.getVelocity().y = sin_val.getIntVal();

	}
}
