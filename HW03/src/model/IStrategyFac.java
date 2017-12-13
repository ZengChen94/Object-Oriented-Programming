package model;

import model.strategy.IUpdateStrategy;

/**
 * Interface for Strategy Factories
 * Only functions to make new Update strategies
 * 
 * @author cz39
 * @author ker7
 */
public interface IStrategyFac {
	/**
	 * makes a new IUpdateStrategy, which just called loadStrategy to make a new strategy for a ball
	 * 
	 * @return Returns a strategy in the form of an IUpdateStrategy object
	 */
	public IUpdateStrategy make();
}
