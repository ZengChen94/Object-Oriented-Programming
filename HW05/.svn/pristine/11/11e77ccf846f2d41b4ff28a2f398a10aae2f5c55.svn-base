package model;

import java.awt.Component;

/**
 * Interface that goes from the model to the view that enables the model to talk to the view.
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 *
 */
public interface IModel2ViewAdapter {
	/**
	 * The method that tells the view to repaint the canvas
	 */
	public void repaint();

	/**
	 * The method is to return the canvas to paint 
	 * @return The Component canvas object
	 */
	public Component getCanvas();

	/**
	 * No-op "null" adapter.
	 * See the web page on the Null Object Design Pattern.
	 */
	public static final IModel2ViewAdapter NULL_OBJECT = new IModel2ViewAdapter() {
		public void repaint() {
		}

		public Component getCanvas() {
			return null;
		}
	};
}