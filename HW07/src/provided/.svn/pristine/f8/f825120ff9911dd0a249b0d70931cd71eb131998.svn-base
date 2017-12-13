package provided.compute;

/**
 * A local view adapter for a task.
 * <b>This adapter is NOT an RMI Server object!!</b>
 * This adapter is for LOCAL use ONLY!   <b>This adapter should NOT be serializable!</b>
 * This adapter should NEVER be transmitted to anyone.<br/>
 * <br/>
 * Whenever an ITask is received, the local system should immediately give an 
 * instance of this adapter to the received ITask object to connect it into 
 * the local system.<br/>   
 * <em>ITask objects should be careful NOT to include this adapter when they 
 * are serialized</em> and to always default their reference to 
 * ILocalTaskViewAdapter.DEFAULT_ADAPTER when they are being deserialized.
 * 
 * @author Stephen Wong
 * 
 */
public interface ILocalTaskViewAdapter {

	/**
	 * Default view adapter that simply prints to standard out.
	 */
	public static final ILocalTaskViewAdapter DEFAULT_ADAPTER = new ILocalTaskViewAdapter() {
		public void append(String s) {
			System.out.println("[ITaskViewAdapter.DEFAULT_ADAPTER.append()] " + s);
		}
	};

	/**
	 * Append the given string to the view's display
	 * @param s the string to display
	 */
	public void append(String s);
}