package cz39_zx18.client.mvc.model;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import common.*;
import common.datatype.chatroom.IAddReceiverType;
import cz39_zx18.client.utils.AddReceiverType;
import cz39_zx18.client.utils.ILocalCmd2ModelAdapter;
import cz39_zx18.client.utils.IUser2ModelAdapter;
import cz39_zx18.client.utils.NarrowString;
import cz39_zx18.client.utils.ProxyUser;
import cz39_zx18.client.utils.Receiver;
import cz39_zx18.client.utils.User;
import provided.mixedData.IMixedDataDictionary;
import provided.mixedData.MixedDataDictionary;
import provided.mixedData.MixedDataKey;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * Main mcv model
 */
public class MainModel {
	private List<MiniMVCAdapter<ProxyUser>> miniModels = new LinkedList<MiniMVCAdapter<ProxyUser>>();
	private MainModel2ViewAdapter mainModel2ViewAdpt;

	private Consumer<String> consumer;
	private IRMIUtils rmiUtils;
	private Registry registry = null;
	private User user;
	private IUser userStub;
	private final int boundport = IRMI_Defs.STUB_PORT_CLIENT;
	private int receivePort = IRMI_Defs.STUB_PORT_EXTRA;

	/**
	 * Constructor for MainModel
	 * @param mainModel2ViewAdpt adapter talking to main view
	 */
	public MainModel(MainModel2ViewAdapter mainModel2ViewAdpt) {
		this.mainModel2ViewAdpt = mainModel2ViewAdpt;

		consumer = new Consumer<String>() {
			@Override
			public void accept(String t) {
				mainModel2ViewAdpt.append(t);
			}
		};
		rmiUtils = new RMIUtils(consumer);
	}

	/**
	 * Start the model
	 */
	public void start() {
		String addr = "127.0.0.1";
		try {
			addr = rmiUtils.getLocalAddress();
		} catch (SocketException | UnknownHostException e) {
			consumer.accept("[MainModel.start()] " + e);
			e.printStackTrace();
		}
		mainModel2ViewAdpt.setServerName(addr);
	}

	/**
	 * Start the RMI server
	 * @param username the name of the user
	 * @param servername server's name (local ip address)
	 */
	public void startServer(String username, String servername) {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);
		String name = username + "@" + servername;
		user = new User(name, new IUser2ModelAdapter() {
			@Override
			public void addConnected(IUser userStub) {
				mainModel2ViewAdpt.addConnected(userStub);
			}

			@Override
			public void joinChatRoom(IChatRoom room) {
				new ChatRoomManager(room);
			}

			@Override
			public void appendMsg(String text, String label) {
				// TODO Auto-generated method stub
				mainModel2ViewAdpt.appendUserMsg(text, label);
			}

			@Override
			public void buildScrollableComponent(IComponentFactory fac, String label) {
				// TODO Auto-generated method stub
				mainModel2ViewAdpt.addScrollComp(fac.makeComponent(), label);
			}

			@Override
			public void buildNonScrollableComponent(IComponentFactory fac, String label) {
				// TODO Auto-generated method stub
				mainModel2ViewAdpt.addNonScrollComp(fac.makeComponent(), label);
			}

			@Override
			public <T> T put(MixedDataKey<T> key, T value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T get(MixedDataKey<T> key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T extends IUserMessageType> void sendTo(IUser target, Class<T> id, T data) {
				// TODO Auto-generated method stub
				try {
					target.receive(new DataPacketUser<T>(id, data, userStub));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		consumer.accept("[MainModel.startServer()]: You have logged in as " + name);
		registry = rmiUtils.getLocalRegistry();
		try {
			userStub = (IUser) UnicastRemoteObject.exportObject(user, boundport);
		} catch (RemoteException e) {
			consumer.accept("[MainModel.startServer()]: Exception when export object " + e.getMessage());
			e.printStackTrace();
		}
		try {
			registry.rebind(IUser.BOUND_NAME, userStub);
			mainModel2ViewAdpt.addConnected(userStub);
			consumer.accept(
					"[MainModel.startServer()]: Registry successfully bind " + name + " To " + IUser.BOUND_NAME);
		} catch (RemoteException e) {
			consumer.accept("[MainModel.startServer()]: Exception when rebinging user " + e.getMessage());
			e.printStackTrace();
		}

		//Create user communication GUI
		//		mainModel2ViewAdpt.makeMiniMVC(room, user, receiver);
	}

	private class ChatRoomManager {
		Receiver receiver;
		MiniMVCAdapter<ProxyUser> miniAdpt;
		IReceiver receiverStub;
		IMixedDataDictionary dict = new MixedDataDictionary();

		ChatRoomManager(IChatRoom room) {
			this.receiver = new Receiver(room, userStub, new ILocalCmd2ModelAdapter() {
				@Override
				public void addReceiver(IReceiver receiverStub) {
					try {
						miniAdpt.addItem(new ProxyUser(receiverStub.getUserStub()));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void removeReceiver(IReceiver receiverStub) {
					try {
						miniAdpt.removeItem(new ProxyUser(receiverStub.getUserStub()));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void appendMsg(String text, String name) {
					System.out.println("MiniAdpt == null " + miniAdpt == null);
					miniAdpt.append("[" + name + "]: " + text);
				}

				@Override
				public void buildScrollableComponent(IComponentFactory fac, String label) {
					miniAdpt.addScrollableComponent(fac.makeComponent(), label);
				}

				@Override
				public void buildNonScrollableComponent(IComponentFactory fac, String label) {
					miniAdpt.addNonScrollableComponent(fac.makeComponent(), label);
				}

				@Override
				public <T> T put(MixedDataKey<T> key, T value) {
					// TODO Auto-generated method stub
					System.out.println("[put] ------Implemented------");
					return dict.put(key, value);
				}

				@Override
				public <T> T get(MixedDataKey<T> key) {
					// TODO Auto-generated method stub
					System.out.println("[get] ------Implemented------");
					return dict.get(key);
				}

				@Override
				public <T extends ICRMessageType> void sendToChatRoom(Class<T> id, T data) {
					// TODO Auto-generated method stub
					System.out.println("[sendToChatRoom] ------Not Implemented------");
					room.sendPacket(new DataPacketCR<T>(id, data, receiverStub));
				}

				@Override
				public <T extends ICRMessageType> void sendTo(IReceiver target, Class<T> id, T data) {
					// TODO Auto-generated method stub
					try {
						target.receive(new DataPacketCR<T>(id, data, receiverStub));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public String getName() {
					// TODO Auto-generated method stub
					try {
						return user.getName();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			});
			try {
				receiverStub = (IReceiver) UnicastRemoteObject.exportObject(receiver, receivePort);
				receiver.setReceiverStub(receiverStub);
				receivePort += 2;
				room.sendPacket(new DataPacketCR<IAddReceiverType>(IAddReceiverType.class,
						new AddReceiverType(receiverStub), receiverStub));

				consumer.accept("Create a new chat room " + room.getName());
				this.miniAdpt = mainModel2ViewAdpt.makeMiniMVC(room, user, receiver);
				miniModels.add(this.miniAdpt);
				room.addIReceiverStub(receiverStub);
				miniAdpt.addItem(new ProxyUser(userStub));
			} catch (RemoteException e) {
				e.printStackTrace();
				return;
			}

			try {
				System.out.println(
						"[MainModel].createChatRoom:  receiver getUserName " + receiver.getUserStub().getName());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		ChatRoomManager(String name) {
			this(user.createChatRoom(name));
		}
	}

	/**
	 * Create a chat room with a given name
	 * @param name name of the chat room
	 */
	public void createChatRoom(String name) {
		new ChatRoomManager(name);
	}

	/**
	 * Connect to a remote host
	 * @param remoteHost address of remote host
	 * @return a proxy user
	 */
	public ProxyUser connectToPeer(String remoteHost) {
		consumer.accept("Grabbing the registry from " + remoteHost);
		Registry reg = rmiUtils.getRemoteRegistry(remoteHost);
		consumer.accept("Found the remote registry " + reg);

		IUser remoteUser = null;
		try {
			remoteUser = (IUser) reg.lookup(IUser.BOUND_NAME);
			if (remoteUser.equals(userStub)) {
				consumer.accept("[MainModel.connectTo()] you are connected to localhost");
				return null;
			}
			remoteUser.connect(userStub);
			return new ProxyUser(remoteUser);
		} catch (RemoteException | NotBoundException e) {
			consumer.accept("[MainModel.connectTo()] exception when getting IUser from " + reg);
			e.printStackTrace();
			return null;
		}
	}

	//	/**
	//	 * 
	//	 * @param remoteHost
	//	 * @return
	//	 */
	//	public ProxyUser connectToGameServer(String remoteHost) {
	//		consumer.accept("Grabbing the registry from " + remoteHost);
	//		Registry reg = rmiUtils.getRemoteRegistry(remoteHost);
	//		consumer.accept("Found the remote registry " + reg);
	//		
	//		IUser remoteUser = null;
	//		try {
	//			remoteUser = (IUser) reg.lookup(IUser.BOUND_NAME);
	//			if (remoteUser.equals(userStub)) {
	//				consumer.accept("[MainModel.connectTo()] you are connected to localhost");
	//				return null;
	//			}
	//			remoteUser.connect(userStub);
	//			return new ProxyUser(remoteUser);
	//		} catch (RemoteException | NotBoundException e) {
	//			consumer.accept("[MainModel.connectTo()] exception when getting IUser from " + reg);
	//			e.printStackTrace();
	//			return null;
	//		}
	//	}
	//	
	/**
	 * Stop the model, exit the application
	 */
	public void stop() {
		for (MiniMVCAdapter<ProxyUser> miniModel : miniModels)
			miniModel.quit();

		try {
			registry.unbind(IUser.BOUND_NAME);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			System.exit(-1); // exit the program.
		}
		rmiUtils.stopRMI();
		System.exit(0);
	}

	/**
	 * Request chat room list from a remote user
	 * @param remoteUser the remote user
	 */
	public void request(ProxyUser remoteUser) {
		consumer.accept("Request chat rooms from " + remoteUser);
		Iterable<IChatRoom> rooms = null;
		try {
			rooms = remoteUser.getChatRooms();
		} catch (RemoteException e) {
			consumer.accept("Exception when getting chat rooms from the user " + remoteUser);
			e.printStackTrace();
			return;
		}

		ArrayList<IChatRoom> tempRooms = new ArrayList<IChatRoom>();
		rooms.forEach(tempRooms::add);
		Object[] chatRooms = tempRooms.toArray();

		Object choose = JOptionPane.showInputDialog(null, "Choose one chat room", "Request chat room",
				JOptionPane.INFORMATION_MESSAGE, null, chatRooms, chatRooms.length > 0 ? chatRooms[0] : null);
		if (choose == null) {
			consumer.accept("No Chat Room Selected");
			return;
		}
		IChatRoom cr = (IChatRoom) choose;
		System.out.println("User join chat room " + cr.getName() + " curnum " + cr.getIReceiverStubs().size());

		if (user.joinChatRoom(cr))
			new ChatRoomManager(cr);
	}

	/**
	 * Get stub of the local user
	 * @return stub of the local user
	 */
	public IUser getUserStub() {
		return userStub;
	}

	/**
	 * Send a message to a target user
	 * @param text the message text
	 * @param target the target user
	 */
	public void sendMsg(String text, ProxyUser target) {
		try {
			target.receive(new DataPacketUser<NarrowString>(NarrowString.class, new NarrowString(text), userStub));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
