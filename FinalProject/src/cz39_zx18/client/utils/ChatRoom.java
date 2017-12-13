package cz39_zx18.client.utils;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import common.*;

/**
 * ChatRoom defines a set of operations of a chat room, implement interface IChatRoom
 * @author Chen Zeng, Zhihui Xie
 */
public class ChatRoom implements IChatRoom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6930767046527822299L;
	private String name;
	private Set<IReceiver> receiverStubs;
	private UUID id;

	/**
	 * Constructor for ChatRoom, create a chat room with a given name
	 * @param name the name of the chat room
	 */
	public ChatRoom(String name) {
		this.name = name;
		receiverStubs = new CopyOnWriteArraySet<IReceiver>();
		id = UUID.randomUUID();
	}

	/**
	 * Return the name of chatroom
	 * @return name Name of the chatroom
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Return the name of chatroom
	 * @return name Name of the chatroom
	 */
	public String toString() {
		return name;
	}

	/**
	 * Return the UUID of chatroom
	 * @return id UUID of the chatroom
	 */
	@Override
	public UUID getUUID() {
		return id;
	}

	/**
	 * Return the ReceiverStubs of chatroom
	 * @return receiverStubs ReceiverStubs of chatroom
	 */
	@Override
	public Collection<IReceiver> getIReceiverStubs() {
		return receiverStubs;
	}

	/**
	 * Send datapacket
	 * @param data Datapacket in chatroom
	 */
	@Override
	public <T extends ICRMessageType> void sendPacket(DataPacketCR<T> data) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (IReceiver receiver : receiverStubs) {
					try {
						receiver.receive(data);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		}) {
		}.start();
	}

	/**
	 * Add receiver of chatroom
	 * @param receiver Receiver of chatroom
	 * @return boolean Whether added successfully
	 */
	@Override
	public boolean addIReceiverStub(IReceiver receiver) {
		return receiverStubs.add(receiver);
	}

	/**
	 * Remove receiver of chatroom
	 * @param receiver Receiver of chatroom
	 * @return boolean Whether removed successfully
	 */
	@Override
	public boolean removeIReceiverStub(IReceiver receiver) {
		return receiverStubs.remove(receiver);
	}

	/**
	 * Judge whether chatroom if equal
	 * @param other Judged chatroom
	 * @return boolean Whether equal
	 */
	public boolean equals(Object other) {
		if (other instanceof ChatRoom) {
			return id.equals(((IChatRoom) other).getUUID());
		}
		return false;
	}

	/**
	 * Return hashCode of UUID
	 * @return hashCode HashCode of UUID
	 */
	public int hashCode() {
		return id.hashCode();
	}

}
