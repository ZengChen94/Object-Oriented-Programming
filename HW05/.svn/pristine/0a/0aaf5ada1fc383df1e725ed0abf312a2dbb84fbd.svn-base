package model.strategy;

import java.awt.Point;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * The StopStrategy, every time it interact, the target ball will be stopped
 * @author Ye Wang
 * @author Chen Zeng
 * @version 1.0
 */

// Or, using lambda expressions:
public class StopStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	public void init(Ball context) {
		//no-op
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// No-op
		dispatcher.dispatch((TDispMsg) new IBallCmd() {
			@Override
			public void apply(Ball target, IDispatcher<IBallCmd> dispatcher) {
				if (context != target) {
					double distance = context.getLocation().distance(target.getLocation());
					if (distance <= (context.getRadius() + target.getRadius()))
						target.setVel(new Point(0, 0));
				}
			}
		});
	}
}
