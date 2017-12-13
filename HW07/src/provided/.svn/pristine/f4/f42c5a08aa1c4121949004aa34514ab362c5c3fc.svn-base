package provided.client.model.taskUtils;

import java.rmi.RemoteException;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

/**
 * An implementation of ITaskFactoryLoader that loads ITaskFactory instances defined by a 
 * singleton object in a static field of the desired ITask class called "FACTORY".<br/>
 * <br/>
 * If this loader attempts to load a non-existent ITask class, an internally-defined
 * ErrorTask instance will be returned.   This error task simply echos its constructor 
 * parameter when executed.
 *  
 * @author Stephen Wong
 *
 */
public class SingletonTaskFactoryLoader implements ITaskFactoryLoader {
	/**
	 * Task used in error situations.  This task simply echoes its contructor's parameter.
	 * @author Stephen Wong
	 *
	 */
	private static final class ErrorTask implements ITask<String> {

		private static final long serialVersionUID = -1511071283026818965L;
		transient private ILocalTaskViewAdapter viewAdapter;
		private String param;

		public ErrorTask(String param) {
			this.param = param;
		}
		
		@Override
		public String execute() throws RemoteException {
			String result = "param = "+param;
			viewAdapter.append(result);
			return result;
		}

		@Override
		public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
			this.viewAdapter = viewAdapter;
		}

		@Override
		public ITaskResultFormatter<String> getFormatter() {
			return new ITaskResultFormatter<String>() {

				@Override
				public String format(String result) {
					return "ErrorTask: result = "+result;
				}
			};
		}
		
	}
	
	/**
	 * Singleton instance of this class
	 */
	public static final ITaskFactoryLoader SINGLETON = new SingletonTaskFactoryLoader();
	
	/**
	 * Private constructor for use by singleton only
	 */
	private SingletonTaskFactoryLoader() {}
	
	/* (non-Javadoc)
	 * @see provided.compute.ITaskFactoryLoader#load(java.lang.String)
	 */
	@Override
	public ITaskFactory<?> load(String classname) {
		try {
			return (ITaskFactory<?>) Class.forName(classname).getDeclaredField("FACTORY").get(null);
		}
		catch (Exception e) {
			return new ITaskFactory<String>() {

				@Override
				public ITask<String> make(String param) {
					return new ErrorTask(param);
				}
				
				public String toString() {
					return ErrorTask.class.getName();
				}
			};
		}
	}

}
