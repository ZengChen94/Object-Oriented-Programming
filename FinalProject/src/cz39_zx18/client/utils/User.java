package cz39_zx18.client.utils;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import common.DataPacketUserAlgoCmd;
import common.DataPacketUser;
import common.IChatRoom;
import common.IUser;
import common.IUserCmd2ModelAdapter;
import common.datatype.IRequestCmdType;
import common.datatype.user.IInvitationType;
import common.datatype.user.IQuitType;
import common.IUserMessageType;
import common.datatype.user.IUserInstallCmdType;
import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.extvisitor.IExtVisitorCmd;

/**
 * Implementation of IUser defining a user in a chat app
 */
public class User implements IUser {

	private String name;
	private List<IChatRoom> chatRooms;
	private UUID id;
	private IUser2ModelAdapter modelAdpt;
	private List<IUser> connectedUsers;
	protected Map<Class<?>, List<DataPacketUser<?>>> messageCache;

	final DataPacketAlgo<String, String> algo = new DataPacketAlgo<String, String>(
			new DataPacketUserAlgoCmd<IUserMessageType>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 7985116744569301060L;

				@Override
				public String apply(Class<?> id, DataPacketUser<IUserMessageType> host, String... params) {
					Object x = host.getData();
					String name = "Unknown remote name ";
					try {
						name = host.getSender().getName();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					modelAdpt.appendMsg("[Algo.apply() ] Default case called. successfully process data " + x, name);
					List<DataPacketUser<?>> cache = messageCache.getOrDefault(id, new LinkedList<DataPacketUser<?>>());
					cache.add(host);
					messageCache.put(id, cache);
					try {
						host.getSender().receive(new DataPacketUser<IRequestCmdType>(IRequestCmdType.class,
								new RequestCmdType(id), User.this));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				}
			});

	/**
	 * Constructor for User
	 * @param name the name of the user
	 * @param modelAdpt adapter supporting auto-connect-back feature
	 */
	public User(String name, IUser2ModelAdapter modelAdpt) {
		this.name = name;
		this.modelAdpt = modelAdpt;
		chatRooms = new LinkedList<IChatRoom>();
		id = UUID.randomUUID();
		connectedUsers = new LinkedList<IUser>();
		this.messageCache = new HashMap<Class<?>, List<DataPacketUser<?>>>();

		algo.setCmd(IInvitationType.class, new DataPacketUserAlgoCmd<IInvitationType>() {
			private static final long serialVersionUID = 2052439064398222693L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IInvitationType> host, String... params) {
				System.out.println("[User] Receive Invitation ----- ");
				modelAdpt.joinChatRoom(host.getData().getChatRoom());
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		algo.setCmd(IQuitType.class, new DataPacketUserAlgoCmd<IQuitType>() {
			private static final long serialVersionUID = -7873519335701805905L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IQuitType> host, String... params) {
				System.out.println("[User receive IQuitType message]----Not Implemented");
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		algo.setCmd(IRequestCmdType.class, new DataPacketUserAlgoCmd<IRequestCmdType>() {
			private static final long serialVersionUID = -7856523715319555526L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IRequestCmdType> host, String... params) {
				Class<?> cmdId = host.getData().getCmdId();
				IExtVisitorCmd<String, Class<?>, String, ADataPacket> extVisitorCmd = algo.getCmd(cmdId);
				DataPacketUserAlgoCmd<?> cmd = (DataPacketUserAlgoCmd<?>) extVisitorCmd;
				try {
					host.getSender().receive(new DataPacketUser<IUserInstallCmdType>(IUserInstallCmdType.class,
							new IUserInstallCmdType() {
								private static final long serialVersionUID = 6805669667356851301L;

								@Override
								public DataPacketUserAlgoCmd<?> getCmd() {
									return cmd;
								}

								@Override
								public Class<?> getCmdId() {
									return cmdId;
								}
							}, User.this));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {

			}
		});

		algo.setCmd(IUserInstallCmdType.class, new DataPacketUserAlgoCmd<IUserInstallCmdType>() {
			private static final long serialVersionUID = 4795901236158766777L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IUserInstallCmdType> host, String... params) {
				Class<?> cmdId = host.getData().getCmdId();
				DataPacketUserAlgoCmd<?> cmd = host.getData().getCmd();
				cmd.setCmd2ModelAdpt(modelAdpt);
				algo.setCmd(cmdId, cmd);
				for (DataPacketUser<?> packet : messageCache.get(cmdId))
					packet.execute(algo);
				messageCache.remove(cmdId);
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {

			}
		});
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public void connect(IUser userStub) throws RemoteException {
		modelAdpt.addConnected(userStub);
		connectedUsers.add(userStub);
	}

	@Override
	public Collection<IChatRoom> getChatRooms() throws RemoteException {
		return chatRooms;
	}

	/**
	 * Join a chat room
	 * @param chatRoom the chat room to join
	 * @return false if already in  the chat room; true if successfully joined the chat room
	 */
	public boolean joinChatRoom(IChatRoom chatRoom) {
		for (IChatRoom cr : chatRooms)
			if (cr.getUUID().equals(chatRoom.getUUID()))
				return false;
		return chatRooms.add(chatRoom);
	}

	/**
	 * Leave a chat room
	 * @param chatRoom the chat room to leave
	 * @return false if not in  the chat room; true if successfully leaved the chat room
	 */
	public boolean leaveChatRoom(IChatRoom chatRoom) {
		return chatRooms.remove(chatRoom);
	}

	/**
	 * Create a chat room using a given name
	 * @param name name of the chat room to be created
	 * @return a new chat room object with a given name
	 */
	public IChatRoom createChatRoom(String name) {
		IChatRoom chatRoom = new ChatRoom(name);
		this.joinChatRoom(chatRoom);
		return chatRoom;
	}

	public String toString() {
		return name + "  UUID: " + id;
	}

	@Override
	public UUID getUUID() throws RemoteException {
		return id;
	}

	@Override
	public <T extends IUserMessageType> void receive(DataPacketUser<T> data) throws RemoteException {
		data.execute(algo);
	}

	/**
	 * Get a list of connected users
	 * @return a list of connected users
	 */
	public List<IUser> getConnectedUsers() {
		return connectedUsers;
	}
}
