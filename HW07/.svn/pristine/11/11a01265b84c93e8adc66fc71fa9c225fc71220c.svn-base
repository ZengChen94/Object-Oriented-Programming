package client.model.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

/**
 * Task that calculates square
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 */
public class GetSquare implements ITask<Integer> {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8609148713023144457L;

	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;
	/**
	 * Input
	 */
	private int input;
	/**
	 * Output result
	 */
	private int result;

	/**
	 * Constructor
	 */
	public GetSquare(String input) {
		this.input = Integer.valueOf(input);
		taskView.append("GetSquare constructing...");
	}

	/**
	 * This method execute task
	 * @return result The result of the task
	 */
	@Override
	public Integer execute() throws RemoteException {
		taskView.append("Executing GetSquare task with " + input + "\n");
		this.result = this.input * this.input;
		return result;
	}

	/**
	 * This method initialize adapter
	 * @param viewAdapter Adapter of client stub
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		this.taskView = viewAdapter;
		viewAdapter.append("GetSquare installed!\n");
		System.out.println("GetSquare.setTaskViewAdapter called with input: " + this.input + ".\n");
	}

	/**
	 * This method return result
	 * @return result The result of the task
	 */
	@Override
	public ITaskResultFormatter<Integer> getFormatter() {
		return new ITaskResultFormatter<Integer>() {
			public String format(Integer result) {
				return "GetSquare: System properties = " + result + "  (input = " + input + ")";
			}
		};
	}

	/**
	 * An ITaskFactory for this task
	 */
	public static final ITaskFactory<Integer> FACTORY = new ITaskFactory<Integer>() {

		@Override
		public ITask<Integer> make(String param) {
			return new GetSquare(param);
		}

		@Override
		public String toString() {
			return GetSquare.class.getName();
		}

	};

	/**
	 * Reinitializes transient fields upon deserialization.  See the 
	 * a href="http://download.oracle.com/javase/6/docs/api/java/io/Serializable.html"
	 * Serializable marker interface docs.
	 * taskView is set to a default value for now (ILocalTaskViewAdapter.DEFAULT_ADAPTER).
	 * @param stream The object stream with the serialized data
	 * @throws IOException if the input stream cannot be read correctly
	 * @throws ClassNotFoundException if the class file associated with the input stream cannot be located.
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject(); // Deserialize the non-transient data
		taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER; // re-initialize the transient field
	}
}
