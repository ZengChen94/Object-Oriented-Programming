package model.strategy;

import java.awt.Color;

import model.Ball;

/**
 * Strategy for ball that can turn invisible and visible
 * 
 * @author cz39
 * @author ker7
 */
public class DisappearStrategy implements IUpdateStrategy {
	/**
	 * Counter for keeping track of how long a ball stays invisible
	 **/
	private int counter = 0;

	/**
	 * Causes ball to disappear and reappear incrementally while preserving its other attributes
	 */
	@Override
	public void updateState(Ball ball) {
		int red = ball.getColor().getRed();
		int green = ball.getColor().getGreen();
		int blue = ball.getColor().getBlue();
		counter++;
		if (counter >= 20) {
			ball.setColor(new Color(red, green, blue, 0));
			if (counter >= 40) {
				counter = 0;
			}
		} else
			ball.setColor(new Color(red, green, blue, 255));
	}
}
