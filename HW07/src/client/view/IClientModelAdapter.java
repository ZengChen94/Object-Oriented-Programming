package client.view;

/**
 * The interface of control adapter from the view to the model
 * that enables the view to talk to the model.
 * Adapter that the view uses to communicate to the model for non-repetitive control tasks.
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 * 
 * @param <TDropListItem> The items in the droplist.
 */

public interface IClientModelAdapter<TDropListItem> {
	/**
	 * This method quit app
	 */
	void quit();

	/**
	 * This method sends message
	 * @param text Message to be sent
	 */
	void sendMsg(String text);

	/**
	 * This method connect the client to engine
	 * @param text Remote ip address
	 * @return result The reply of connection
	 */
	String connectTo(String text);

	/**
	 * This method add tasks
	 * @param text Task name to be added
	 * @return result The reply of addTask
	 */
	TDropListItem addTask(String text);

	/**
	 * This method run tasks
	 * @param task Name of the task
	 * @param parameter The parameter of the task
	 */
	void runTask(TDropListItem task, String parameter);

	TDropListItem combineTask(TDropListItem task1, TDropListItem task2);

}
