package provided.engine.model;


/**
 * <p>This interface is provided as a guide for building the compute engine model.
 * This interface is not in any way fundamental to the operation of RMI, though 
 * the behaviors described here are critical to proper RMI operation.</p>
 * 
 * <p>The behaviors as described here are REQUIRED for any engine server implementation 
 * though the use of this particular interface is not.</p>
 * 
 * <p><b>IMPORTANT: The engine model is NOT the same as the compute engine!!</b>  
 * The compute engine (ICompute) is just one of the objects managed by the engine
 * model.</p>
 * 
 * <p>Suggestion: Copy this 
 * interface to one's own code to use as a starting point and then modify it to 
 * suit one's specific needs.</p>
 * 
 * @author Stephen Wong
 *
 */
public interface IEngineModel {

	/**
	 * Start the RMI Server (ICompute engine): <br/>
	 * 1. (Do this FIRST!) Use the provided IRMIUtils to start the RMI system, using port = IRMI_Defs.CLASS_SERVER_PORT_SERVER.  
	 * This will also automatically start the the security manager and the class file server for remote dynamic
	 * class loading.<br/>
	 * 2. Instantiate the ICompute server, saving a reference to it in the model somewhere. <br/>
	 * 3. Create a stub of the ICompute server. <br/>
	 * 4. Use the IRMIUtils to get the LOCAL Registry.  Save the reference somewhere for use later. <br/>
	 * 5. Bind the ICompute engine stub to the local Registry using the name and port defined by ICompute 
	 * locating the local Registry and binding a STUB of an ICompute engine instance to the that registry.  
	 */
	void start();

	/**
	 * Stops the engine model by unbinding the ICompute engine from the Registry 
	 * and stopping class file server.   This MUST be called before exiting the system! 
	 * Procedure: <br/>
	 * 1. First, unbind the ICompute server stub from the local Registry. <br/>
	 * 2. Use the IRMIUtils to stop the RMI system.
	 */
	void stop();

	/**
	 * Send string message to connected remote client using the IRemoteTaskViewAdapter STUB 
	 * received from the client..   The message should also be echoed to the local 
	 * engine server's user interface.
	 * @param text  Message to send
	 */
	void sendMsgToClient(String text);

}