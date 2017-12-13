package client.model;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.Consumer;

import client.model.task.MultiTask;
import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ICompute;
import provided.compute.IRemoteTaskViewAdapter;
import provided.compute.ITask;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * 
 * Model for Client
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class ClientModel implements IClientModel {
	/**
	 * Constructor
	 * @param viewAdpt The adapter to the view
	 */
	public ClientModel(IClientViewAdapter viewAdpt) {
		this._viewAdapter = viewAdpt;
	}

	/**
	 * The adapter to the view
	 */
	private IClientViewAdapter _viewAdapter;
	/**
	 * Compute engine
	 */
	private ICompute computeEngineStub;
	/**
	 * Server task view adapter stub
	 */
	private IRemoteTaskViewAdapter _serverTVAStub;
	/**
	 * Client task view adapter stub
	 */
	private IRemoteTaskViewAdapter _clientTVAStub = null;

	private IRemoteTaskViewAdapter clientTA = new IRemoteTaskViewAdapter() {
		public void append(String s) {
			_viewAdapter.append("[Server] " + s);
		}
	};
	/**
	 * Command
	 */
	private Consumer<String> outputCmd = new Consumer<String>() {
		@Override
		public void accept(String t) {
			_viewAdapter.append(t);
		}
	};

	IRMIUtils rmiUtils = new RMIUtils(outputCmd);

	/**
	 * Start the RMI
	 */
	@Override
	public void start() {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);
		try {
			_viewAdapter.setRemoteHost(rmiUtils.getLocalAddress());
		} catch (Exception e) {
			System.err.println("Error getting local address: " + e);
		}
	}

	/**
	 * Stop the RMI
	 */
	@Override
	public void stop() {
		System.out.println("ClientModel.stop(): client is quitting.");
		try {
			rmiUtils.stopRMI();

		} catch (Exception e) {
			System.err.println("ClientModel.stop(): Error stopping class server: " + e);
		}
		System.exit(0);
	}

	/**
	 * Connect to remote server
	 * @param remoteHost The address of remote host
	 * @return result The connection result
	 */
	@Override
	public String connectTo(String remoteHost) {
		// TODO Auto-generated method stub
		try {
			_viewAdapter.append("Locating registry at " + remoteHost + "...\n");
			Registry registry = rmiUtils.getRemoteRegistry(remoteHost);
			_viewAdapter.append("Found registry: " + registry + "\n");
			computeEngineStub = (ICompute) registry.lookup(ICompute.BOUND_NAME);
			_viewAdapter.append("Found remote Compute object: " + computeEngineStub + "\n");
			if (null == _clientTVAStub) {
				_clientTVAStub = (IRemoteTaskViewAdapter) UnicastRemoteObject.exportObject(clientTA,
						IRemoteTaskViewAdapter.BOUND_PORT_CLIENT);
			}
			_serverTVAStub = computeEngineStub.setTextAdapter(_clientTVAStub);
			_serverTVAStub.append("Hello from client [Chen@Team2]:");
			//			_serverTVAStub = comp.setTextAdapter(clientTAstub);
			//			_serverTVAStub = null;
		} catch (Exception e) {
			_viewAdapter.append("Exception connecting to " + remoteHost + ": " + e + "\n");
			e.printStackTrace();
			return "No connection established!";
		}
		return "Connection to " + remoteHost + " established!";
	}

	/**
	 * Send message to client
	 * @param text The contents of the message to be sent
	 */
	@Override
	public void sendMsgToComputeEngine(String text) {
		if (null != _serverTVAStub) {
			_viewAdapter.append("[Sending msg to connected remote host] msg = \"" + text + "\"\n");
			try {
				_serverTVAStub.append(text);
			} catch (RemoteException e) {
				_viewAdapter.append("[RemoteException while sending msg to connected remote host:  " + e + "] msg = \""
						+ text + "\"\n");
				e.printStackTrace();
			}
		} else {
			_viewAdapter.append(
					"[Error sending msg to connected remote host: serverTVAStub is null!] msg = \"" + text + "\"\n");
		}
	}

	/**
	 * Run task
	 * @param task The type of the task
	 */
	@Override
	public <T> String runTask(ITask<T> task) {
		if (computeEngineStub == null)
			return "No ICompute object yet!";
		try {
			T result = computeEngineStub.executeTask(task);
			System.out.println("runTask result: " + result);
			_viewAdapter.append("runTask result: " + result + "\n");
			return result.toString();
		} catch (Exception e) {
			_viewAdapter.append("Compute GetInfo exception: " + e + "\n");
			e.printStackTrace();
		}
		return null;
	}
	
	public ITaskFactory<?> combineTask(ITaskFactory<?> task1, ITaskFactory<?> task2){
		if (task1 == null || task2 == null)
			return null;
		return new ITaskFactory<ArrayList<String>>() {
			@Override
			public ITask<ArrayList<String>> make(String param) {
				return new MultiTask(task1.make(param), task2.make(param));
			}
			
			public String toString() {
				return task1.toString() + "-" + task2.toString();
			}
		};
	}
}
