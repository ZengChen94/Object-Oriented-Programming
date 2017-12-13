package engine.model;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;

import provided.compute.ICompute;
import provided.compute.IRemoteTaskViewAdapter;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * The Model of Engine
 *
 * @author Zhenwei Feng
 * @author Chen Zeng
 * @version 1.0
 *
 */
public class EngineModel implements IEngineModel {
	/**
	 * Constructor
	 */
	public EngineModel(IEngineViewAdapter view) {
		this._viewAdapter = view;
	}

	/**
	 * View Adapter
	 */
	private IEngineViewAdapter _viewAdapter;
	/**
	 * Registry
	 */
	private Registry registry;
	/**
	 * Engine
	 */
	private ComputeEngine engine;
	/**
	 * Command
	 */
	private Consumer<String> outputCmd = new Consumer<String>() {
		@Override
		public void accept(String t) {
			_viewAdapter.append(t);
		}
	};

	private IRMIUtils _rmiUtils = new RMIUtils(outputCmd);

	/**
	 * Start the RMI
	 */
	@Override
	public void start() {
		_rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);
		try {
			engine = new ComputeEngine(outputCmd);
			_viewAdapter.append("Instantiated ComputeEngine: " + engine + "\n");
			ICompute stub = (ICompute) UnicastRemoteObject.exportObject(engine,
					IRemoteTaskViewAdapter.BOUND_PORT_SERVER);
			registry = _rmiUtils.getLocalRegistry();
			registry.rebind(ICompute.BOUND_NAME, stub);
			_viewAdapter.append("ComputeEngine bound to " + ICompute.BOUND_NAME + "\n");
		} catch (Exception e) {
			System.err.println("ComputeEngine exception:" + "\n");
			e.printStackTrace();
			System.exit(-1);
		}
		_viewAdapter.append("Waiting..." + "\n");
	}

	/**
	 * Stop the RMI
	 */
	@Override
	public void stop() {
		try {
			registry.unbind(ICompute.BOUND_NAME);
			System.out.println("ComputeEngine: " + ICompute.BOUND_NAME + " has been unbound.");
			_rmiUtils.stopRMI();
			System.exit(0);
		} catch (Exception e) {
			System.err.println("ComputeEngine: Error unbinding " + ICompute.BOUND_NAME + ":\n" + e);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Send message to client
	 * @param text The contents of the message to be sent
	 */
	@Override
	public void sendMsgToClient(String text) {
		engine.sendMsgToClient(text);
	}

}
