package client.model;

/**
 * Interface that goes from the model to the view that enables the model to talk to the view.
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 *
 */
public interface IClientViewAdapter {
	/**
	 * This method set remote host ip
	 * @param localAddress Value of remote ip address
	 */
	void setRemoteHost(String localAddress);

	/**
	 * This method adds text to text area
	 * @param string Text to be added
	 */
	void append(String string);

}
