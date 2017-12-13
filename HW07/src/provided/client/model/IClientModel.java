package provided.client.model;

import provided.compute.ITask;

/**
 * <p>This interface is provided as a guide for building the client-side model.
 * This interface is not in any way fundamental to the operation of RMI, though 
 * the behaviors described here are critical to proper RMI operation.</p>
 * 
 * <p>The behaviors as described here are REQUIRED for any client implementation 
 * though the use of this particular interface is not.</p>
 * 
 * <p>Suggestion: Copy this 
 * interface to one's own code to use as a starting point and then modify it to 
 * suit one's specific needs.</p>
 * 
 * <p>Discussion point: Notice that this interface is completely decoupled from the notion of ITaskFactory's.  
 * Should this model work with factories or with the tasks directly?  In the past, many people have
 * had their models work with the factories.  But some people have argued that the factory operations,
 * e.g. creating a new factory, combining factories and even getting the product of the factory are
 * completely separate, decoupled operations from the rest of the model and thus should be encapsulated 
 * separately. What do YOU believe?</p>
 * 
 * @author Stephen Wong
 *
 */

public interface IClientModel {

	/**
	 * Starts the client model by initializing the RMI system and creating a remote view adapter stub 
	 * for use by an engine server.  Procedure:<br/>
	 * 1. (DO THIS FIRST!) Use the IRMIUtils to start the RMI system, using 
	 * port = IRMI_Defs.CLASS_SERVER_PORT_CLIENT.  
	 * This will also automatically start the the security manager and the class file server for remote dynamic
	 * class loading.<br/>
	 * 2. Export a STUB from the client's IRemoteTaskViewAdapter RMI server object.  
	 * Save this stub for use when connecting to an engine server. <br/>
	 * 
	 */
	void start();

	/**
	 * Stops the client model by using the IRMIUtils to stop the RMI system.
	 * This automatically stops class file server.   
	 * This MUST be called before exiting the system! 
	 */
	void stop();

	/**
	 * Connects to the given remote host's Registry and retrieves the stub to the ICompute object bound 
	 * to the ICompute.BOUND_NAME name in the remote Registry on port 
	 * IRMI_Defs.REGISTRY_PORT.  Procedure:<br/> 
	 * 1. Use the IRMIUtils to retrieve the remote Registry at the given IP address. <br/>
	 * 2. Use the remote Registry to retrieve an ICompute STUB bound to the name defined by IComputer.BOUND_NAME
	 * Save the reference to the IComputer stub somewhere.<br/>
	 * 3. Use the ICompute stub to give the client's IRemoteTaskViewAdapter STUB to the remote engine server and
	 *   retrieve the compute engine's IRemoteTaskViewAdapter stub.  Save the retrieved stub for later use.  
	 *   Use the retrieved stub to send a connection status message to the remote compute engine's user interface.<br/> 
	 * 4. Return a string indicating the success or failure of the connection attempt.
	 * 
	 * @param remoteHost The IP address or host name of the remote server.
	 * @return  A status string on the connection.
	 */
	String connectTo(String remoteHost);

	/**
	 * Sends a string message to the connected compute engine using the IRemoteTaskViewAdapter STUB
	 * received from the engine server.   This message should also be echoed to the local user interface.
	 * @param text The message to be sent
	 */
	void sendMsgToComputeEngine(String text);

	/**
	 * Runs the given ITask on the remote engine server, returning the String formatted
	 * result, which is produced using the given ITask's ITaskResultFormatter object. 
	 * @param task The task to run on the remote engine server.
	 * @param <T> The return type of the give ITask
	 * @return A string representation of the task results, using the task's formatter object.
	 */
	<T> String runTask(ITask<T> task);

}