package model.strategy;

import model.Ball;
import util.Randomizer;

/**
 * Strategy for a simple color changing ball that changes colors after 20 ticks of update state
 * 
 * @author cz39
 * @author ker7
 */
public class ColorChangingStrategy implements IUpdateStrategy {
	private final int total = 20;
	private int cnt = total;

	/**
	 * Random change the color of the ball.
	 */
	@Override
	public void updateState(Ball ball) {
		cnt--;
		if (cnt == 0) {
			ball.setColor(Randomizer.singleton.randomColor());
			cnt = total;
		}
	}
}
