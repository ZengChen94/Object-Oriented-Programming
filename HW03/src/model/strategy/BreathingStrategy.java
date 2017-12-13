package model.strategy;

import model.Ball;
import util.Randomizer;

/**
 * Strategy for a breathing (radius expanding and collapsing) ball
 * 
 * @author cz39
 * @author ker7
 */
public class BreathingStrategy implements IUpdateStrategy {
	private final int total = 20;
	private int cnt = total;

	/**
	 * Random change the radius of the ball.
	 */
	@Override
	public void updateState(Ball ball) {
		cnt--;
		if (cnt == 0) {
			ball.setRadius(Randomizer.singleton.randomInt(15, 50));
			cnt = total;
		}
	}
}
