package cz39_zx18.client.utils;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.UUID;

import common.DataPacketUser;
import common.IChatRoom;
import common.IUser;
import common.IUserMessageType;

/**
 * ProxyUser is a wrapper class of IUser, to support toString() and equals() method
 */
public class ProxyUser implements IUser {

	private IUser stub;
	private UUID id;
	private String name;

	/**
	 * Create a proxy user using a user stub
	 * @param user a remote user stub
	 */
	public ProxyUser(IUser user) {
		this.stub = user;
		try {
			this.id = user.getUUID();
			this.name = user.getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get name of proxyUser
	 * @return name The name of proxyUser
	 */
	@Override
	public String getName() throws RemoteException {
		return name;
	}

	/**
	 * Get chatrooms
	 * @return chatrooms The chatrooms of proxyUser
	 */
	@Override
	public Collection<IChatRoom> getChatRooms() throws RemoteException {
		return stub.getChatRooms();
	}

	/**
	 * Get UUID
	 * @return id The UUID of proxyUser
	 */
	@Override
	public UUID getUUID() throws RemoteException {
		return id;
	}

	/**
	 * Connect to user
	 * @param userStub The user stub to be connected
	 */
	@Override
	public void connect(IUser userStub) throws RemoteException {
		stub.connect(userStub);
	}

	/**
	 * Get the name
	 * @return name The name of proxy
	 */
	public String toString() {
		//		String name = null;
		//		try {
		//			name =  stub.getName();
		//		} catch (RemoteException e) {
		//			e.printStackTrace();
		//			System.out.println("User is out of reach ...... ");
		//		}
		return name;
	}

	/**
	 * Judge whether proxyUsers are equal
	 * @param other Object to be judged
	 * @return bool Whether proxyUsers are equal
	 */
	public boolean equals(Object other) {
		if (other instanceof ProxyUser) {
			try {
				return id.equals(((ProxyUser) other).getUUID());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Get the hashcode of proxyUser
	 * @return hashCode The hashcode of proxyUser
	 */
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * Receive the data
	 * @param data Datapackect received
	 */
	@Override
	public <T extends IUserMessageType> void receive(DataPacketUser<T> data) throws RemoteException {
		// TODO Auto-generated method stub
		stub.receive(data);
	}
}
