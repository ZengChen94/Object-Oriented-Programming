package view;

/**
 * Following is the class defining the adapter that will connect the view-to-model communication for controlling actions
 * 
 * @author cz39
 * @author ker7
 */
public interface IModelCtrlAdapter<TDropListItem> {

	/**
	 * Clears all of the balls currently on the canvas
	 */
	public void clearBalls();

	/**
	 * Loads a ball from a supplied class name from a droplist
	 * 
	 * @param selectedItem A string designated to hold a ball represented by a class name in the form of model.balls.className
	 */
	public void loadBall(TDropListItem selectedItem);

	/**
	 * Combines two droplist items and then returns a new droplist item representing the combination
	 * 
	 * @param item1 The first droplist item
	 * @param item2 The second droplist item
	 * @return IDropListItem Returns a new droplist item that is the combination of two other items
	 */
	public TDropListItem combineStrategies(TDropListItem item1, TDropListItem item2);

	/**
	 * Creates a ball that can switch its strategies
	 */
	public void makeSwitcherBall();

	/**
	 * Switches the current strategy to a different one designated by an item in the droplist
	 * 
	 * @param item The item in the droplist to set the new strategy to
	 */
	public void switchStrategy(TDropListItem item);

	/**
	 * Adds a strategy to the droplist
	 * 
	 * @param classname String to add to the droplist in the form of a strategy classname
	 * @return TDropListItem Returns a droplist item representing the new strategy that was added
	 */
	public TDropListItem addStrategy(String classname);

	/**
	 * No-op "null" adapter
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IModelCtrlAdapter<Object> NULL_OBJECT = new IModelCtrlAdapter<Object>() {
		public void clearBalls() {
		}

		public void loadBall(Object selectedItem) {
		}

		public Object combineStrategies(Object item1, Object item2) {
			return null;
		}

		public void makeSwitcherBall() {
		}

		public void switchStrategy(Object item) {
		}

		public Object addStrategy(String classname) {
			return null;
		}
	};
}
