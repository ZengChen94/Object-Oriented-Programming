package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import util.Dispatcher;
import javax.swing.Timer;
import util.Randomizer;
import model.strategy.*;

/**
 * Following is the class defining the current model being used for BallWorld
 * 
 * @author cz39
 * @author ker7
 */
public class BallWorldModel {
	/**
	 * A _timeSlice set to allowed the animation to update every 50 milliseconds
	 */
	private int _timeSlice = 50;

	/**
	 * Observable of all the balls
	 * Allows easy control of balls (creation and removal)
	 */
	private Dispatcher myDispatcher = new Dispatcher();

	/**
	 * The model-to-view adapter object
	 * Set to NULL using the "Null Pattern"
	 */
	private IViewAdapter _model2ViewAdpt = IViewAdapter.NULL_OBJECT;

	/**
	 * Timer object to update the ball animation
	 */
	private Timer _timer = new Timer(_timeSlice, new ActionListener() {
		/**
		 * The timer "ticks" by calling this method every _timeslice milliseconds
		 * 
		 * @param e The event that represents what occurs on each timer tick
		 */
		public void actionPerformed(ActionEvent e) {
			_model2ViewAdpt.update();
		}
	});

	private SwitcherStrategy switcherStrategy;

	/**
	 * BallWorldModel constructor
	 * 
	 * @param model2ViewAdpt the interface that the model uses to talk to the view
	 */
	public BallWorldModel(IViewAdapter model2ViewAdpt) {
		_model2ViewAdpt = model2ViewAdpt;
	}

	/**
	 * called when view asked to paint the balls
	 * 
	 * @param g The object that will define how to paint the balls
	 */
	public void update(Graphics g) {
		myDispatcher.notifyAll(g);
	}

	/**
	 * Start the timer ticking
	 * Call initially from the controller.start() method to begin the model
	 */
	public void start() {
		_timer.start();
	}

	/**
	 * Add a ball into the dispatcher observable with a given strategy type
	 * 
	 * @param strat The strategy type of the ball that will be generated in the application
	 */
	public void dispatch_balls(IUpdateStrategy strat) {
		Point startLoc = Randomizer.singleton.randomLoc(_model2ViewAdpt.getCanvas().getSize());
		int startRadius = Randomizer.singleton.randomInt(10, 20);
		Point startVel = Randomizer.singleton.randomVel(new Rectangle(0, 0, 10, 25));
		Color startColor = Randomizer.singleton.randomColor();
		Component theCanvas = _model2ViewAdpt.getCanvas();
		Ball single_ball = new Ball(startLoc, startRadius, startVel, startColor, theCanvas, strat);
		myDispatcher.addObserver(single_ball);
	}

	/**
	 * Wipe the canvas of all balls currently displaying
	 */
	public void clear_all_balls() {
		myDispatcher.deleteObservers();
	}

	/**
	 * Like the loadBall function of the last homework, loadStrategy loads a strategy type for the creation of a ball when provided with the name of the strategy
	 * 
	 * @param strategyName a user inputed string representing the name of the strategy
	 * @return IUpdateStrategy If successful, function will return the strategy in the form of an IUpdateStrategy object
	 */
	public IUpdateStrategy loadStrategy(String strategyName) {
		try {
			Object[] args = new Object[] {};
			java.lang.reflect.Constructor<?> cs[] = Class.forName(strategyName).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IUpdateStrategy) c.newInstance(args); // Call the constructor.   Will throw a null ptr exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + strategyName + " failed to load.");
			ex.printStackTrace();
			return _errorStrategyFac.make();
		}

	}

	/**
	 * Function dedicated to making the strategy factory which involves loading a strategy given the name of a class
	 * 
	 * @param classname given the name of a class, it will initiate the loading of a strategy for a new ball
	 * @return IStrategyFac Returns a strategy factory
	 */
	public IStrategyFac makeStrategyFac(final String classname) {
		if (classname == null)
			return _errorStrategyFac;
		IStrategyFac Strategy = new IStrategyFac() {
			public IUpdateStrategy make() {
				return loadStrategy(fixName(classname));
			}

			public String toString() {
				return classname;
			}
		};
		return Strategy;
	}

	/**
	 * Used to get the Switcher Strategy that will allow balls to switch their current strategy to a new one in the list
	 * 
	 * @return IUpdateStrategy returns the strategy in the form of an IUpdateStrategy object that will change the ball
	 */
	public IUpdateStrategy getSwitcherStrategy() {
		if (switcherStrategy == null)
			switcherStrategy = new SwitcherStrategy();
		return switcherStrategy;
	}

	/**
	 * Combines strategy factories to allow balls of multiple strategies to be present in the application
	 * 
	 * @param stratFac1 First strategy to combine
	 * @param stratFac2 Second strategy to combine
	 * @return Returns a strategy factory of the combined strategies, allowing the creation of "composite" balls
	 */
	public IStrategyFac combineStrategyFacs(final IStrategyFac stratFac1, final IStrategyFac stratFac2) {
		//null case
		if ((stratFac1 == null) || (stratFac2 == null))
			return _errorStrategyFac;
		IStrategyFac CombineStrategy = new IStrategyFac() {
			public IUpdateStrategy make() {
				return new MultiStrategy(stratFac1.make(), stratFac2.make());
			}

			public String toString() {
				return stratFac1.toString() + "-" + stratFac2.toString();
			}
		};
		return CombineStrategy;
	}

	/**
	 * Sets a ball's current strategy to a new one for the switcher
	 * 
	 * @param newStrategy The new strategy to change the ball to
	 */
	public void switchSwitcherStrategy(IUpdateStrategy newStrategy) {
		switcherStrategy.setStrategy(newStrategy);
	}

	/**
	 * Fixes the name being provided by the user to match the way the strategy is represented by the code
	 * 
	 * @param classname The name of the class being fixed
	 * @return Returns a string of the whole package and strategy e.g. model.strategy.StraightStrategy
	 */
	private String fixName(String classname) {
		String fixedName = "model.strategy." + classname + "Strategy";
		return fixedName;
	}

	/**
	 * This is the method that is called by the view's adapter to the model, i.e. is called by IView2ModelAdapter.paint().
	 * This method will update the sprites's painted locations by painting all the sprites
	 * onto the given Graphics object.
	 * 
	 * @param g The Graphics object from the view's paintComponent() call.
	 */
	public void paint(Graphics g) {
		myDispatcher.notifyAll(g); // The Graphics object is being given to all the sprites (Observers)
	}

	/**
	 * A factory for a beeping error strategy that beeps the speaker every 25 updates.
	 * Either use the _errorStrategyFac variable directly if you need a factory that makes an error strategy,
	 * or call _errorStrategyFac.make() to create an instance of a beeping error strategy.
	 */
	private IStrategyFac _errorStrategyFac = new IStrategyFac() {
		@Override
		/**
		 * Make the beeping error strategy
		 * @return  An instance of a beeping error strategy
		 */
		public IUpdateStrategy make() {
			return new IUpdateStrategy() {
				private int count = 0; // update counter

				@Override
				/**
				 * Beep the speaker every 25 updates
				 */
				public void updateState(Ball context) {
					if (25 < count++) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						count = 0;
					}
				}
			};
		}
	};
}