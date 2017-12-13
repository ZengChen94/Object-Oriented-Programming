package model;

/**
 * The IPaintStrategyFac interface
 * 
 * @author Ye Wang
 * @author Chen Zeng
 * @version 1.0
 * 
 */
public interface IPaintStrategyFac {

	/**
	 * @return IPaintStrategy
	 */
	public IPaintStrategy make();
}
