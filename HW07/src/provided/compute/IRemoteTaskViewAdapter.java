package provided.compute;

import java.rmi.*;

/**
 * A remote view adapter that enables a task or any other part of a system to display
 * a text message on the user interface of a remote system.<br/>
 * <b>This adapter IS an RMI Server object!</b>
 * Instantiations of this interface should NEVER be sent to a remote machine, <em>only
 * STUBs made from this Remote object should ever be transmitted.</em>
 * @author swong
 *
 */
public interface IRemoteTaskViewAdapter extends Remote {
	/**
	 * The port that the client will use to 
	 * communicate with the IRemoteTaskViewAdapter object on the server.  
	 * Note that this port must be opened for inbound traffic on the machine 
	 * where the adapter actually resides (the Server).
	 */
	public static final int BOUND_PORT_SERVER = 2101;

	/**
	 * A secondary port that the server will use to 
	 * communicate with the IRemoteTaskViewAdapter object on the client.  
	 * Note that this port must be opened for inbound traffic on the machine 
	 * where the adapter actually resides (the client).  
	 * The client and server must use different ports in order to run on 
	 * the same machine.
	 */
	public static final int BOUND_PORT_CLIENT = 2102;

	/**
	 * Append the given string to the remote view's display
	 * @param s the string to display
	 * @throws RemoteException thrown if a network error occurs
	 */
	public void append(String s) throws RemoteException; 

	/**
	 * Null adapter object that only prints the given string to the standard err output.
	 */
	public static final IRemoteTaskViewAdapter NULL_ADAPTER = new IRemoteTaskViewAdapter() {

		@Override
		public void append(String s) throws RemoteException {
			System.err.println("[IRemoteTaskViewAdapter.NULL_ADAPTER.append()] The following message was NOT transmitted: "+s);
		}
	};

}
