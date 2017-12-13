package model;

import java.awt.Component;

/**
 * Following is the class defining the adapter that will connect the model-to-view communication
 * 
 * @author cz39
 * @author ker7
 */
public interface IViewAdapter {

	/**
	 * The model wants the view to be able to update
	 * update() allows the model to tell the view when it should update
	 */
	public void update();

	/**
	 * Obtains the canvas being drawn on by the view and returns it
	 * 
	 * @return Returns a Component object which is the "most abstract" entity that has a width and height
	 */
	public Component getCanvas();

	/**
	 * No-op "null" adapter
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IViewAdapter NULL_OBJECT = new IViewAdapter() {

		public Component getCanvas() {
			return null;
		}

		public void update() {
		}
	};

}
