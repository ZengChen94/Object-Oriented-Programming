package view;

/**
 * Interface that goes from the view to the model that enables the model to talk to the view, taking part in control works.
 * 
 * @author Chen Zeng (cz39)
 * @author Rong Hu (rh41)
 * @version 20170908
 * @since 20170908
 */

public interface IView2ModelCtrlAdapter {
	/**
	 * This method clear the balls.
	 */
	public void clearBalls();

	/**
	 * This method load in the ball as type of ballType.
	 * @param ballType The type of ABall to be added.
	 */
	public void loadBall(String ballType);

	/**
	 * No-op singleton implementation of IView2ModelCtrlAdapter 
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IView2ModelCtrlAdapter NULL_OBJECT = new IView2ModelCtrlAdapter() {
		public void loadBall(String ballType) {
		}

		public void clearBalls() {
		}
	};
}
