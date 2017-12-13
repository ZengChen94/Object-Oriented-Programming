package provided.client.model.taskUtils;

import provided.compute.ITask;

/**
 * Abstract factory for creating ITasks
 * @param <T> The return type of the ITask this factory creates.
 * @author Stephen
 *
 */
public interface ITaskFactory<T> {
	
	/**
	 * Makes an ITask using the given string parameter.  Note that the ITask
	 * implementation may require something other than a String as its 
	 * constructor parameter, so the factory is responsible for 
	 * converting the given string to the required task constructor parameter
	 * type.
	 * @param param A string representation of the constructor parameter for the task. 
	 * @return an ITask instance.
	 */
	public ITask<T> make(String param);

}
