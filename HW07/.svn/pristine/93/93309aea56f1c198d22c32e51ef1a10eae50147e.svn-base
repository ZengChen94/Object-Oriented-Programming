package client.model.task;

import java.rmi.RemoteException;

import java.io.*;

import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;
import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ILocalTaskViewAdapter;

/**
 * Task that gets the server's system properties plus demonstrates that it can carry 
 * internal data to a remote system when the entire object is serialized and sent
 * over. 
 * @author swong
 *
 */
public class GetInfo implements ITask<String> {

	/**
	 * UID for well-defined serialization
	 */
	private static final long serialVersionUID = -3690660506537207490L;

	/**
	 * Adapter to the local view.  Marked "transient" so that it is not serialized
	 * and instead is reattached at the destination (the server).  
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * Input string given to the constructor
	 */
	private String input = "";

	/**
	 * An array carried along to prove that internal data is transmitted too.
	 */
	private double[] dArray = new double[] { 1.2, 2.3, 4.5 };

	/**
	 * Constructor for the class.
	 * 
	 * @param input  A string to carry around.
	 */
	public GetInfo(String input) {
		this.input = input;
	}

	/**
	 * Display the internal string on the server's console.
	 * Get the server's system properties and calculate the sum of the internal data.  
	 * @return the server's system properties and the sum of the internal array as a string.
	 * @throws RemoteException if a network error occurs
	 */
	@Override
	public String execute() throws RemoteException {
		taskView.append("GetInfo task called with input = " + input + "\n");
		double sum = 0.0;
		for (double x : dArray)
			sum += x;
		return System.getProperties().toString() + "\n Sum = " + sum;
	}

	/**
	 * Reinitializes transient fields upon deserialization. See the <a href=
	 * "http://download.oracle.com/javase/6/docs/api/java/io/Serializable.html">
	 * Serializable</a> marker interface docs.
	 * taskView is set to a default value for now (ILocalTaskViewAdapter.DEFAULT_ADAPTER).
	 * 
	 * @param stream
	 *            The object stream with the serialized data
	 * @throws IOException if the input stream cannot be read correctly
	 * @throws ClassNotFoundException if the class file associated with the input stream cannot be located.
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject(); // Deserialize the non-transient data
		taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER; // re-initialize the transient field
	}

	/**
	 * Sets the task view adapter to a new value. Tests connection by sending a
	 * string representation of the dArray structure.  Called by the server to 
	 * attach the task to its view.
	 * 
	 * @param viewAdapter
	 *            the adapter to the view.
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		this.taskView = viewAdapter;
		viewAdapter.append("GetInfo installed!\n");
		String s = "";
		for (double x : dArray)
			s += x + " ";
		System.out.println("GetInfo.setTaskViewAdapter called.\ndArray = " + s + "\n");
	}

	/**
	 * Returns an formatter that creates a string of the form:
	 * "GetInfo: System properties = [result] (input = [input])"
	 * Notice how the "input" value is not part of the result, but that the
	 * formatter can use it anyway because the formatter is closing over 
	 * the whole task.
	 */
	@Override
	public ITaskResultFormatter<String> getFormatter() {
		return new ITaskResultFormatter<String>() {
			public String format(String result) {
				return "GetInfo: System properties = " + result + "  (input = " + input + ")";
			}
		};
	}

	/**
	 * An ITaskFactory for this task
	 */
	public static final ITaskFactory<String> FACTORY = new ITaskFactory<String>() {

		@Override
		public ITask<String> make(String param) {
			return new GetInfo(param);
		}

		@Override
		public String toString() {
			return GetInfo.class.getName();
		}

	};
}
