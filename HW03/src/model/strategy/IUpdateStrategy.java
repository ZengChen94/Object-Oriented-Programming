package model.strategy;

import model.Ball;

/**
 * Update strategy interface that functions for updating the current state of a ball
 * 
 * @author cz39
 * @author ker7
 */
public interface IUpdateStrategy {

	/**
	 * Updates the current state when provided a Ball object
	 * 
	 * @param ball ball object meant to be updated
	 */
	public void updateState(Ball ball);
}
