package model;

import util.Dispatcher;

/**
 * Interface that represents commands sent through the dispatcher to process the balls.
 * 
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 */
@FunctionalInterface
public interface IBallCmd {
	/**
	 * The method run by the ball's update method which is called when the ball is updated by the dispatcher.
	 * @param ball The ball that is calling this method. The context under which the command is to be run.
	 * @param disp 
	 */
	public void apply(CBall ball, Dispatcher disp);
}
