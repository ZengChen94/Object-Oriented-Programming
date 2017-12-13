package client.model.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

public class MultiTask implements ITask<ArrayList<String>>{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8780669441181081385L;

	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;
	
	private ITask<?> task1;
	
	private ITask<?> task2;
	
	private ArrayList<String> result = new ArrayList<>();
	
	public MultiTask(ITask<?> task1, ITask<?> task2) {
//		System.out.println(task1.toString());
//		System.out.println(task2.toString());//here works
		this.task1 = task1;
		this.task2 = task2;
		
	}

	@Override
	public ArrayList<String> execute() throws RemoteException {
//		System.out.println(task1.toString());
//		System.out.println(task2.toString());
//		System.out.println(task1.execute());
//		System.out.println(task2.execute());
		taskView.append("Executing MultiTask task with task " + task1.toString() + " and task " + task2.toString() + "\n");
		result.add(String.valueOf(task1.execute()));
		result.add(String.valueOf(task2.execute()));
		return result;
	}

	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		this.taskView = viewAdapter;
		viewAdapter.append("MultiTask installed!\n");
//		System.out.println("MultiTask.setTaskViewAdapter called with input: " + this.input + ".\n");
	}

	@Override
	public ITaskResultFormatter<ArrayList<String>> getFormatter() {
		return new ITaskResultFormatter<ArrayList<String>>() {
			public String format(ArrayList<String> result) {
				return "(1) " + result.get(0) + "\n(2) " + result.get(1);
			}
		};
	}
	
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
