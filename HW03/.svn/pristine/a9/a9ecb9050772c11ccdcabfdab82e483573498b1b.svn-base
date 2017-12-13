package model.strategy;

import model.Ball;

/**
 * Strategy for balls that will be able to switch between the multitude of strategies
 * 
 * @author cz39
 * @author ker7
 */
public class SwitcherStrategy implements IUpdateStrategy {

	/**
	 * The strategy of the ball in question
	 */
	private IUpdateStrategy strategy;

	/**
	 * Constructor for SwitcherStrategy to initially have a basic straight ball
	 */
	public SwitcherStrategy() {
		this.strategy = new StraightStrategy();
	}

	/**
	 * Updates the current strategy of the ball in question
	 */
	@Override
	public void updateState(Ball ball) {
		strategy.updateState(ball);
	}

	/**
	 * Allows setting of the current strategy
	 * 
	 * @param newStrategy The new strategy to set the ball to
	 */
	public void setStrategy(IUpdateStrategy newStrategy) {
		this.strategy = newStrategy;
	}
}