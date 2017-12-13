package provided.compute;

/**
 * A specialized format converter that takes the return value of a matched ITask object
 * and converts it into a String representation.   This formatter is capable of more than 
 * a simple toString() of the task result; it can format the result in anyway that it 
 * chooses and/or add additional textual elements to make the string output "prettier" and/or
 * more easily readable.<br/>
 * Since the formatter must be matched to the task, instances of this interface are 
 * generally produced by factory methods on the ITask object itself.
 *
 * @param <T> The return type of the task whose results this formatter will format. 
 * 
 * @author Stephen Wong
 */
public interface ITaskResultFormatter<T> {
	/**
	 * Format the result of the matched task's execution.
	 * @param result  The execution result of the matched task's execution.
	 * @return A string representation of that result, including any additional formatting and/or text.
	 */
	public String format(T result); 
}
