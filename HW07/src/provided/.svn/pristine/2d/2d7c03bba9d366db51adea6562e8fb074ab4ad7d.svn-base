package provided.client.model.taskUtils;

/**
 * Utility interface for dynamically loading ITaskFactory's that is independent of the return type
 * of the ITasks created.   This is useful for creating an design that is able to work with 
 * arbitrary tasks but remain decoupled from the task return types.
 * @author Stephen Wong
 *
 */
public interface ITaskFactoryLoader {

	/**
	 * Dynamically load an ITaskFactory instance given its class name. 
	 * @param classname  The fully qualified class name of the desired ITaskFactory class.
	 * @return An ITaskFactory instance.
	 */
	ITaskFactory<?> load(String classname);

}