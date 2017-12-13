package model.strategy;

import model.Ball;

/**
 * Class for allowing balls to hold multiple strategies at once
 * 
 * @author cz39
 * @author ker7
 */
public class MultiStrategy implements IUpdateStrategy {

	/**
	 * first strategy to be combined for a ball
	 */
	private IUpdateStrategy strategy1;

	/**
	 * Second strategy to be combined for a ball
	 */
	private IUpdateStrategy strategy2;

	/**
	 * Constructor for MultiStrategy meant to set the current strategies to the ones being sent
	 * 
	 * @param strategy1 First strategy to be comibned for a ball
	 * @param strategy2 Second strategy to be combined for a ball
	 */
	public MultiStrategy(IUpdateStrategy strategy1, IUpdateStrategy strategy2) {
		this.strategy1 = strategy1;
		this.strategy2 = strategy2;
	}

	/**
	 * Overrides updateState to update the balls strategies whenever a change occurs
	 */
	@Override
	public void updateState(Ball ball) {
		strategy1.updateState(ball);
		strategy2.updateState(ball);
	}
}