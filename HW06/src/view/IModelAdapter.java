package view;

/**
 * The interface of control adapter from the view to the model
 * that enables the view to talk to the model.
 * Adapter that the view uses to communicate to the model for non-repetitive control tasks.
 * @author Yuchang Shen
 * @author Chen Zeng
 * @version 1.0
 * 
 * @param <TDropListItem> The items in the droplist.
 */

public interface IModelAdapter<TDropListItem> {
	/**
	 * This method load music
	 * @param musicName The name of the music
	 * @return contents The contents of the music
	 */
	public String loadMusic(String musicName);

	/**
	 * This method parse music
	 * @return phrase The phrase of the music
	 */
	public String parseMusic();

	/**
	 * This method play music
	 * reference: https://www.clear.rice.edu/comp504/f17/labs/lab07/
	 * @param instrument The type of instrument
	 */
	public void playMusic(TDropListItem instrument);

	/**
	 * This method stop music
	 */
	public void stopMusic();

	/**
	 * No-op "null" adapter.
	 * See the web page on the Null Object Design Pattern.
	 */
	public static final IModelAdapter<Object> NULL_OBJECT = new IModelAdapter<Object>() {
		@Override
		public String loadMusic(String musicName) {
			return null;
		}

		@Override
		public String parseMusic() {
			return null;
		}

		@Override
		public void playMusic(Object instrument) {
		}

		@Override
		public void stopMusic() {
		}

	};
}
