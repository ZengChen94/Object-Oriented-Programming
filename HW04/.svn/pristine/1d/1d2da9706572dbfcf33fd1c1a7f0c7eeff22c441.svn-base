package view;

/**
 * The interface of control adapter from the view to the model
 * that enables the view to talk to the model.
 * Adapter that the view uses to communicate to the model for non-repetitive control tasks such as manipulating strategies.
 * @author Yue Pan
 * @author Chen Zeng
 * @version 1.0
 * 
 * @param <TDropListItem> The items in the droplist.
 * @param <TPaintDropList> The items in the droplist
 * 
 */
public interface IModelControlAdapter<TDropListItem, TPaintDropList> {

	/**
	 * Take the given short strategy name and return a corresponding something to put onto both drop lists.
	 * @param classname  The shortened class name of the desired strategy
	 * @return Something to put onto both the drop lists.
	 */
	public TDropListItem addStrategy(String classname);

	/**
	 * Make a ball with the selected short strategy name.
	 * @param selectedItem  A shorten class name for the desired strategy
	 * @param selectedPaint  A shorten class name for the desired paint strategy
	 */
	public void makeBall(TDropListItem selectedItem, TPaintDropList selectedPaint);

	/**
	 * Return a new object to put on both lists, given two items from the lists.
	 * @param selectedItem1  An object from one drop list
	 * @param selectedItem2 An object from the other drop list
	 * @return An object to put back on both lists.
	 */
	public TDropListItem combineStrategies(TDropListItem selectedItem1, TDropListItem selectedItem2);

	/**
	 * This method to load a different ball
	 * @param ballname The text input from the text frame
	 * @param paint The paint input
	 */
	public void loadBall(String ballname, String paint);

	/**
	 * Method to switch the switcher.
	 * @param selectedItem The factory to make the new strategy.
	 */
	public void switchStrategy(TDropListItem selectedItem);

	/**
	 * Method to make a switcher.
	 * @param selectedPaint 
	 */
	public void makeSwitcher(TPaintDropList selectedPaint);

	/**
	 * load a paint strategy to the list
	 * @param classname  The shortened class name of the desired paint strategy
	 * @return TPaintDropList in the combobox
	 */
	public TPaintDropList addPaint(String classname);

	/**
	 * Clear all the balls in canvas
	 */
	public void clear();

	/**
	 * IModelControlAdapter
	 */
	public static final IModelControlAdapter<Object, Object> NULL_OBJECT = new IModelControlAdapter<Object, Object>() {
		@Override
		public Object addStrategy(String classname) {
			return null;
		}

		@Override
		public void makeBall(Object selectedItem, Object selectedPaint) {
		}

		@Override
		public Object combineStrategies(Object selectedItem1, Object selectedItem2) {
			return null;
		}

		@Override
		public void loadBall(String ballname, String paint) {
		}

		@Override
		public void clear() {
		}

		@Override
		public void switchStrategy(Object selectedItem) {
		}

		@Override
		public void makeSwitcher(Object selectedItem) {
		}

		@Override
		public Object addPaint(String classname) {
			// TODO Auto-generated method stub
			return null;
		}

	};

}
