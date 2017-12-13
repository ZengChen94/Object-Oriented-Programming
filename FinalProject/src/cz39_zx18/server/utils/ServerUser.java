package cz39_zx18.server.utils;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import common.DataPacketUserAlgoCmd;
import common.DataPacketUser;
import common.IChatRoom;
import common.IUser;
import common.IUserCmd2ModelAdapter;
import common.IUserMessageType;
import common.datatype.IRequestCmdType;
import common.datatype.user.IInvitationType;
import common.datatype.user.IQuitType;
import common.datatype.user.IUserInstallCmdType;
import cz39_zx18.client.utils.ChatRoom;
import cz39_zx18.client.utils.IUser2ModelAdapter;
import cz39_zx18.client.utils.NarrowString;
import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.extvisitor.IExtVisitorCmd;

/**
 * Implementation of IUser defining a user in a chat app
 */
public class ServerUser implements IUser {

	private String name;
	private List<IChatRoom> chatRooms;
	private UUID id;
	private IUser2ModelAdapter modelAdpt;
	private List<IUser> connectedUsers;
	private IUser selfStub;

	final DataPacketAlgo<String, String> algo = new DataPacketAlgo<String, String>(
			new DataPacketUserAlgoCmd<IUserMessageType>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -2726282845789272258L;

				@Override
				public String apply(Class<?> index, DataPacketUser<IUserMessageType> host, String... params) {
					System.out.println("User receives message --------- ");
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
	public ServerUser(String name, IUser2ModelAdapter modelAdpt) {
		this.name = name;
		this.modelAdpt = modelAdpt;
		chatRooms = new LinkedList<IChatRoom>();
		id = UUID.randomUUID();
		connectedUsers = new LinkedList<IUser>();

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

		algo.setCmd(NarrowString.class, new DataPacketUserAlgoCmd<NarrowString>() {
			private static final long serialVersionUID = -5098020801117871239L;

			@Override
			public String apply(Class<?> index, DataPacketUser<NarrowString> host, String... params) {
				try {
					System.out.println(
							"Receive from " + host.getSender().getName() + " String " + host.getData().getData());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
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
							}, selfStub));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
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
		System.out.println(" Remote user connected back --- ");
		connectedUsers.add(userStub);
		//sendTo(userStub, new DataPacketUser<String>(String.class, "hello world", selfStub));
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
	 * @return chatRoom a new chat room object with a given name
	 */
	public IChatRoom createChatRoom(String name) {
		IChatRoom chatRoom = new ChatRoom(name);
		this.joinChatRoom(chatRoom);
		return chatRoom;
	}

	/**
	 * Get name of ServerUser
	 * @return string name+UUID
	 */
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
	 * Send message to a target user
	 * @param remoteUser the target user
	 * @param data the data
	 */
	public void sendTo(IUser remoteUser, DataPacketUser<?> data) {
		try {
			remoteUser.receive(data);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get a list of connected users
	 * @return a list of connected users
	 */
	public List<IUser> getConnectedUsers() {
		return connectedUsers;
	}

	/**
	 * Get the stub of the user
	 * @return the stub of the user
	 */
	public IUser getSelfStub() {
		return selfStub;
	}

	/**
	 * Set the stub of the user
	 * @param selfStub the stub of the user
	 */
	public void setSelfStub(IUser selfStub) {
		this.selfStub = selfStub;
	}
}
