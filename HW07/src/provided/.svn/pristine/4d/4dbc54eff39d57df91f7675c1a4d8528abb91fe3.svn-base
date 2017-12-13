package provided.compute;

import java.io.Serializable;
import java.rmi.*;
/**
 * A abstract task to be run on the computer engine.
 * <b>A task is a FULLY SERIALIZABLE object that is wholly transmitted 
 * from the client to the compute engine. </b>   
 * <em>A task is NOT an RMI Server object!! No stubs should ever be made from this object.</em>
 * @param <T> The type of the returned result of the task
 */
public interface ITask<T> extends Serializable {
  /**
   * Executes the task and returns a result
   * @return The result of executing the task.
   * @throws RemoteException  thrown when a network error occurs.
   */
  public T execute() throws RemoteException;
  
  /**
   * Sets the adapter to the view properly for this object.
   * Note that this setter must be separate from the an ITask implementation's 
   * constructor because the compute engine needs to set this adapter when
   * it receives an ITask from a remote client. 
   * @param viewAdapter an adapter to the view.
   */
  public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter);
  
  /**
   * Get an instance of the matched ITaskResultFormatter for this task.
   * @return A formatter customized for this task object.
   */
  public ITaskResultFormatter<T> getFormatter();
}
