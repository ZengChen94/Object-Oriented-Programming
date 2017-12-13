package view;

import java.awt.Graphics;

/**
 * Following is the class defining the adapter that will connect the view-to-model communication for paint actions
 * 
 * @author cz39
 * @author ker7
 */
public interface IModelPaintAdapter {

	/**
	 * Method for painting all of the balls
	 * Will be used to call update for the provided graphics object
	 * 
	 * @param g The Graphics object that allows the application to draw onto a component
	 */
	public void paintBalls(Graphics g);

	/**
	 * No-op "null" adapter
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IModelPaintAdapter NULL_OBJECT = new IModelPaintAdapter() {
		public void paintBalls(Graphics g) {
		}
	};
}
