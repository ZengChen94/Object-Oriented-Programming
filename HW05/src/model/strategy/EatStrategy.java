package model.strategy;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;

/**
 * The EatStrategy, every time it interact, the target ball will disappear and it's mass will goes to context
 * @author Ye Wang
 * @author Chen Zeng
 * @version 1.0
 */

// Or, using lambda expressions:
public class EatStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	public void init(Ball context) {
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(),
				(contextBall, targetBall, disp) -> disp.deleteObserver(targetBall)));
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
					if (distance <= (context.getRadius() + target.getRadius())) {
						double contextMass = context.getRadius() * context.getRadius();
						double otherMass = target.getRadius() * target.getRadius();
						context.setRadius((int) Math.sqrt(contextMass + otherMass));
						dispatcher.deleteObserver(target);
					}
				}
			}
		});
	}
}
