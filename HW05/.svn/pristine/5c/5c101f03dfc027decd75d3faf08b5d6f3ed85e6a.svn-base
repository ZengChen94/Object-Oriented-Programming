package model.strategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * The BreathingBall strategy 
 * @author Ye Wang
 * @author Chen Zeng
 * @version 1.0
 */
public class BreathingStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	/**
	 * The scale for changing rad.
	 */
	private double scale = 1;
	private double angle = 0;

	/* (non-Javadoc)
	 * @see model.IUpdateStrategy#updateState(model.CBall, util.Dispatcher)
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		scale = 1 + Math.sin(angle);
		angle += 0.5;
		context.setRadius((int) (Math.round(10 * scale)));
	}

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub

	}

}
