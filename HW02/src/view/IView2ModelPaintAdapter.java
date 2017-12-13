package view;

import java.awt.Graphics;

/**
 * Interface that goes from the view to the model that enables the model to talk to the view, taking part in paint works.
 * 
 * @author Chen Zeng (cz39)
 * @author Rong Hu (rh41)
 * @version 20170908
 * @since 20170908
 */

public interface IView2ModelPaintAdapter {
	/**
	 * This method paint balls.
	 * @param g Graphics.
	 */
	public void paintBalls(Graphics g);

	/**
	 * No-op singleton implementation of IView2ModelPaintAdapter 
	 */
	public static final IView2ModelPaintAdapter NULL_OBJECT = new IView2ModelPaintAdapter() {
		public void paintBalls(Graphics g) {

		}
	};
}
