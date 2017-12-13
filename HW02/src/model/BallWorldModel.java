package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Timer;

import util.Dispatcher;
import util.Randomizer;

import balls.*;//can import here?

/**
 * Model for the system.
 * 
 * @author Chen Zeng (cz39)
 * @author Rong Hu (rh41)
 * @version 20170908
 * @since 20170908
 * 
 */

public class BallWorldModel {
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT; // Insures that the adapter is always valid
	private Dispatcher myDispatcher = new Dispatcher();

	private int _timeSlice = 50; // update every 50 milliseconds
	private Timer _timer = new Timer(_timeSlice, (e) -> _model2ViewAdpt.repaint());

	/**
	 * Dynamically make a ball.
	 * @param ballType The type of the ball needed to be made in the canvas.
	 */
	public void makeBall(String ballType) {
		try {
			Component theCanvas = _model2ViewAdpt.getCanvas();
			Point startLoc = Randomizer.Singleton.randomLoc(theCanvas.getSize()); // Location
			Point startVel = Randomizer.Singleton.randomVel(new Rectangle(0, 0, 30, 30)); // Velocity // the first two args don't matter
			int startRadius = Randomizer.Singleton.randomInt(10, 40); // Ball Radius
			Color startColor = Randomizer.Singleton.randomColor(); // Ball Color

			Object[] args = new Object[] {startLoc, startRadius, startVel, startColor, theCanvas};
			java.lang.reflect.Constructor<?> cs[] = Class.forName(ballType).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterCount()) {
					c = cs[i];
					break;
				}
			}
			ABall ball = (ABall) c.newInstance(args);
			myDispatcher.addObserver(ball);
		} catch (Exception ex) {
			System.err.println("Class " + ballType + " failed to load. \nException = \n" + ex);
			ex.printStackTrace(); // print the stack trace to help in debugging
		}
	}

	/**
	 * Clear all balls in this BallModel.
	 */
	public void clearBalls() {
		myDispatcher.deleteObservers();
	}

	/**
	 * Start the timer.
	 */
	public void start() {
		_timer.start();
	}

	/**
	 * Constructor.
	 * @param model2ViewAdpt The IModel2ViewAdapter needed for constructor.
	 */
	public BallWorldModel(IModel2ViewAdapter model2ViewAdpt) {
		_model2ViewAdpt = model2ViewAdpt;
	}

	/**
	 * This is the method that is called by the view's adapter to the model, i.e. is called by IView2ModelAdapter.paint().
	 * This method will update the sprites's painted locations by painting all the sprites
	 * onto the given Graphics object.
	 * @param g The Graphics object from the view's paintComponent() call.
	 */
	public void paint(Graphics g) {
		myDispatcher.notifyAll(g); // The Graphics object is being given to all the sprites (Observers)
	}

	/**
	 * add Observer to Dispatcher.
	 * @param ball ABall needs to be added as Observer.
	 */
	public void add(ABall ball) {
		myDispatcher.addObserver(ball);
	}
}