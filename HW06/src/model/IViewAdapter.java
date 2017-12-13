package model;

import provided.util.ABCInstrument;

/**
 * Interface that goes from the model to the view that enables the model to talk to the view.
 * @author Yuchang Shen
 * @author Chen Zeng
 * @version 1.0
 *
 */
public interface IViewAdapter {
	/**
	 * This method add instrument types to the list
	 * @param instrument Type of the instrument
	 */
	void addInstrument(ABCInstrument instrument);

	/**
	 * No-op "null" adapter.
	 * See the web page on the Null Object Design Pattern.
	 */
	public static final IViewAdapter NULL_OBJECT = new IViewAdapter() {
		@Override
		public void addInstrument(ABCInstrument instrument) {

		}
	};
}
