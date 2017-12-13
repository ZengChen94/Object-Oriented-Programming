package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import model.*;
import view.*;

/**
 * MVC Controller for the system
 * 
 * @author Chen Zeng (cz39)
 * @author Rong Hu (rh41)
 * @version 20170908
 * @since 20170908
 */
public class BallWorldController {

	private BallWorldModel model; // starts off null but will be fine when the constructor is finished.
	private BallWorldView view; // starts off null but will be fine when the constructor is finished.

	/**
	 * Controller constructor builds the system
	 */
	public BallWorldController() {
		// set the model field
		model = new BallWorldModel(new IModel2ViewAdapter() {
			/**
			 * Repaint the view.
			 */
			@Override
			public void repaint() {
				view.update();
			}

			/**
			 * @return Component Canvas of the GUI.
			 */
			@Override
			public Component getCanvas() {
				return view.getCanvas();
			}
		});

		// set the view field
		view = new BallWorldView(new IView2ModelCtrlAdapter() {
			/**
			 * Make a new ball.
			 * @param ballType Type of the ball.
			 */
			@Override
			public void loadBall(String ballType) {
				model.makeBall(ballType);
			}

			/**
			 * Clear all balls.
			 */
			@Override
			public void clearBalls() {
				model.clearBalls();
			}

		}, new IView2ModelPaintAdapter() {
			@Override
			public void paintBalls(Graphics g) {
				model.paint(g);
			}

		});
	}

	/**
	 * Start the system
	 */
	public void start() {
		model.start(); // It is usually better to start the model first but not always.
		view.start();
	}

	/**
	 * Launch the application.
	 * @param args Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					BallWorldController controller = new BallWorldController(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}