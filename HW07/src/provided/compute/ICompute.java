package provided.compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <p>A generalized compute engine object that will execute a given ITask object and 
 * return the result.
 * A compute engine IS an RMI Server object and is itself, NOT transmitted to anyone.
 * ONLY the STUB of this Remote object is ever transmitted.
 * This interface ONLY defines the methods needed by a REMOTE system! </p>
 * 
 * <p>Note that an implementation of this interface may include additional methods 
 * to enable the compute engine to interact with the local system as well as 
 * adapter(s) as needed to communicate with the LOCAL system's model and/or view.</p>   
 * 
 * <p>Any ICompute implementation MUST be able to:<br/>
 * - Display the  ITask computation's results on the Engine system's user interface<br/>
 * - Enable the Engine system to be able to send a text message to the client's user interface.
 * </p> 
 * 
 * <p><b>IMPORTANT: The engine model is NOT the same as the compute engine!!</b>  
 * The compute engine (this interface) is just one of the objects managed by the engine
 * model.</p>
 * 
 * @author Stephen Wong
 */
public interface ICompute extends Remote {
	/**
	 * The name the ICompute object will be bound to in the RMI Registry
	 */
	public static final String BOUND_NAME = "Compute";

	/**
	 * The port that the client will use to communicate with the ICompute object
	 * Note that this port must be opened of inbound traffic on the server machine
	 */
	public static final int BOUND_PORT = 2100;

	/**
	 * Execute the given ITask object and return the result
	 * @param <T> The type of the return value from executing the given ITask object
	 * @param t The ITask object to execute
	 * @return The result from executing the ITask object
	 * @throws RemoteException if a network error occurs 
	 */
	public <T> T executeTask(ITask<T> t) throws RemoteException;

	/**
	 * Sets this object's IRemoteTaskViewAdapter stub so that it can print strings out on the client's user interface.
	 * Returns an IRemoteTaskViewAdapter STUB that will enable the remote client to print strings out on 
	 * the local compute engine's user interface.<br/>
	 * Upon receiving the remote client's IRemoteTaskViewAdapter stub, this method should immediately
	 * test the stub by using it to send a status message to the client. <br/>
	 * Upon receiving the return value of this method, the remote client should immediately 
	 * test the stub by using it to send a status message to back to the compute server. 
	 * 
	 * @param clientTVA  the task view adapter STUB to for the server to use.
	 * @return An adapter STUB to the ICompute server's view.
	 * @throws RemoteException if a network error occurs. 
	 */
	public IRemoteTaskViewAdapter setTextAdapter(IRemoteTaskViewAdapter clientTVAStub) throws RemoteException;

}
