package engine.view;

/**
 * The interface of control adapter from the view to the model
 * that enables the view to talk to the model.
 * Adapter that the view uses to communicate to the model for non-repetitive control tasks.
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 */
public interface IEngineModelAdapter {
	/**
	 * This method quit engine
	 */
	void quit();

	/**
	 * This method sends message to remote client
	 * @param text Contents of message
	 */
	void sendMsgToRemote(String text);

}
